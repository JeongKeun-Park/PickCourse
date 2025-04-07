package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.ReceiveMessageDTO;
import com.app.pickcourse.domain.dto.ReceivePaginationDTO;
import com.app.pickcourse.domain.dto.SendMessageDTO;
import com.app.pickcourse.domain.dto.SendPaginationDTO;
import com.app.pickcourse.domain.vo.*;
import com.app.pickcourse.mapper.MessageMapper;
import com.app.pickcourse.mapper.ReceiveMessageMapper;
import com.app.pickcourse.mapper.SendMessageMapper;
import com.app.pickcourse.repository.*;
import com.app.pickcourse.util.Pagination;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MessageService {

    private final MessageDAO messageDAO; // 슈퍼키 DAO
    private final SendMessageDAO sendMessageDAO; // 보낸 메시지 DAO
    private final ReceiveMessageDAO receiveMessageDAO; // 받은 메시지 DAO
    private final MemberDAO memberDAO;
    private final FileDAO fileDAO;
    private final SendMessageFileDAO sendMessageFileDAO;
    private final ReceiveMessageFileDAO receiveMessageFileDAO;

    // 메세지 보내기
    public void sendMessage(SendMessageDTO sendMessageDTO, MultipartFile file){
        String todayPath = getPath();
        String rootPath = "/upload/" + todayPath;

        // 디버깅 ==
        System.out.println("메시지 전송 시작");
        System.out.println("보낸 사람 ID: " + sendMessageDTO.getSenderId());
        System.out.println("받는 사람 ID: " + sendMessageDTO.getReceiverId());
        System.out.println("메시지 내용: " + sendMessageDTO.getContent());
        // 디버깅 ==

        // 메시지 내용 저장 (슈퍼키)
        MessageVO messageVO = new MessageVO();
        messageVO.setContent(sendMessageDTO.getContent());
        messageDAO.save(messageVO); // DAO 사용

        // 디버깅
        System.out.println("메시지 저장 완료, 메시지 ID: " + messageVO.getId());

        // 보낸 메시지 테이블에 저장
        SendMessageVO sendMessageVO = sendMessageDTO.toVO(); // DTO -> VO 변환
        sendMessageVO.setId(messageVO.getId()); // 슈퍼키 설정
        sendMessageDAO.save(sendMessageVO); // DAO 사용

        // 디버깅
        System.out.println("보낸 메시지 저장 완료, ID: " + sendMessageVO.getId());

        // 받은 메시지 테이블에도 저장
        ReceiveMessageVO receiveMessageVO = new ReceiveMessageVO();
        receiveMessageVO.setId(messageVO.getId()); // 슈퍼키 설정
        receiveMessageVO.setSenderId(sendMessageDTO.getSenderId()); // 보낸 사람 ID
        receiveMessageVO.setReceiverId(sendMessageDTO.getReceiverId()); // 받는 사람 ID
        receiveMessageVO.setContent(sendMessageDTO.getContent()); // 메시지 내용
        receiveMessageDAO.save(receiveMessageVO); // DAO 사용

        // 디버깅
        System.out.println("받은 메시지 저장 완료, ID: " + receiveMessageVO.getId());

        UUID uuid = UUID.randomUUID();
        if(file.getOriginalFilename().equals("")){
            return;
        }
        FileVO fileVO = new FileVO();
        SendMessageFileVO sendMessageFileVO = new SendMessageFileVO();
        ReceiveMessageFileVO receiveMessageFileVO = new ReceiveMessageFileVO();

        fileVO.setFileName(uuid.toString() + "_" + file.getOriginalFilename());
        fileVO.setFileSize(String.valueOf(file.getSize()));
        fileVO.setFilePath(todayPath);

        fileDAO.saveMessageFile(fileVO);
        // 디버깅
        System.out.println("파일 정보 저장 완료, 파일 ID: " + fileVO.getId());

        sendMessageFileVO.setId(fileVO.getId());
        sendMessageFileVO.setSendMessageFileId(sendMessageVO.getId());

        sendMessageFileDAO.saveMessageFile(sendMessageFileVO);
        // 디버깅
        System.out.println("보낸 메시지-파일 관계 저장 완료, ID: " + sendMessageFileVO.getId());

        receiveMessageFileVO.setId(fileVO.getId());
        receiveMessageFileVO.setReceiveMessageFileId(receiveMessageVO.getId());

        receiveMessageFileDAO.saveMessageFile(receiveMessageFileVO);
        // 디버깅
        System.out.println("받은 메시지-파일 관계 저장 완료 ID: " + receiveMessageFileVO.getId());

        File directory = new File(rootPath);
        if(!directory.exists()){
            // 디버깅
            System.out.println("파일이 첨부되지 않음 메시지만 전송 완료");
            directory.mkdirs();
        }

        try {
            file.transferTo(new File(rootPath, uuid.toString() + "_" + file.getOriginalFilename()));
            // 디버깅
            System.out.println("파일 저장 완료: " + rootPath + "/" + uuid.toString() + "_" + file.getOriginalFilename());
    //            썸네일 가공
            if(file.getContentType().startsWith("image")){
                FileOutputStream out = new FileOutputStream(new File(rootPath, "t_" + uuid.toString() + "_" + file.getOriginalFilename()));
                Thumbnailator.createThumbnail(file.getInputStream(), out, 100, 100);
                out.close();
                // 디버깅
                System.out.println("썸네일 생성 완료");
            }
        } catch (IOException e) {
            // 디버깅
            System.err.println("파일 저장 중 오류 발생: " + e.getMessage());
            throw new RuntimeException(e);
        }
        // 디버깅
        System.out.println("메시지 및 파일 전송 완료!");
    }

    private String getPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    // 받은 메세지 조회
    public List<ReceiveMessageDTO> findReceiveMessageByReceiverId(Long receiverId) {
        return receiveMessageDAO.findByReceiverId(receiverId);
    }

    // 보낸 메세지 조회
    public List<SendMessageDTO> findSendMessageBySenderId(Long senderId) {
        return sendMessageDAO.findBySenderId(senderId);
    }

    // 받은 메세지 삭제
    public boolean deleteReceiveMessageById(Long id) {
        // 메시지가 존재하는지 확인
        ReceiveMessageDTO message = receiveMessageDAO.findMessageById(id);
        if (message == null) {
            return false; // 메시지가 존재하지 않으면 삭제 불가
        }

        // 받은 메시지에 첨부된 파일 삭제
        receiveMessageFileDAO.deleteByReceiveMessageId(id);

        // 받은 메시지 삭제
        int deletedRows = receiveMessageDAO.deleteReceiveMessage(id);
        return deletedRows > 0;
    }

    // 보낸 메세지 삭제
    public boolean deleteSendMessageById(Long id) {
        // 메시지가 존재하는지 확인
        SendMessageDTO message = sendMessageDAO.findMessageById(id);
        if (message == null) {
            return false; // 메시지가 존재하지 않으면 삭제 불가
        }

        // 보낸 메시지에 첨부된 파일 삭제
        sendMessageFileDAO.deleteBySendMessageId(id);

        // 보낸 메시지 삭제
        int deletedRows = sendMessageDAO.deleteSendMessage(id);
        return deletedRows > 0;
    }

    // 슈퍼키 메세지 삭제
    public void deleteMessageById(Long id) {
        messageDAO.delete(id);
    }


    // ID로 이메일을 찾고 이메일로 메시지 전송
    public void sendMessageByEmail(SendMessageDTO sendMessageDTO, MultipartFile file) {
        Long receiverId = memberDAO.findIdByEmail(sendMessageDTO.getReceiverEmail())
                .orElseThrow(() -> new RuntimeException("해당 이메일을 가진 사용자가 없습니다."));

        sendMessageDTO.setReceiverId(receiverId);

        sendMessage(sendMessageDTO, file);
    }


    // 받은 메시지 개수 조회
    public int getReceiveMessageCount(Long receiverId) {
        return receiveMessageDAO.countByReceiverId(receiverId);
    }

    // 받은 메시지 목록 조회 (페이지네이션 적용)
    public List<ReceiveMessageDTO> findReceiveMessages(Long receiverId, Pagination pagination) {
        return receiveMessageDAO.findByReceiverIdWithPagination(receiverId, pagination);
    }

    // 보낸 메시지 조회
    public List<SendMessageDTO> findSendMessages(Long senderId, int page, int rowCount) {
        int startRow = (page - 1) * rowCount;
        return sendMessageDAO.findBySenderIdWithPagination(senderId, startRow, rowCount);
    }

    // 보낸 메시지 개수 조회
    public int getSendMessageCount(Long senderId) {
        return sendMessageDAO.countBySenderId(senderId);
    }

//    페이지네이션 리스트
    public ReceivePaginationDTO getReceiveList(Long receiverId, Pagination pagination) {
        ReceivePaginationDTO receivePaginationDTO = new ReceivePaginationDTO();

        pagination.create(receiveMessageDAO.findTotalReceiveMessage(receiverId));
        receivePaginationDTO.setPagination(pagination);
        receivePaginationDTO.setReceiveMessages(receiveMessageDAO.findAllReceiveMessage(receiverId, pagination));
        return receivePaginationDTO;
    }

    public int getTotalReceiveMessage(Long receiverId) {
        return receiveMessageDAO.findTotalReceiveMessage(receiverId);
    }

    //    페이지네이션 리스트
    public SendPaginationDTO getSendList(Long senderId, Pagination pagination) {
        SendPaginationDTO sendPaginationDTO = new SendPaginationDTO();

        pagination.create(sendMessageDAO.findTotalSendMessage(senderId));
        sendPaginationDTO.setPagination(pagination);
        sendPaginationDTO.setSendMessages(sendMessageDAO.findAllSendMessage(senderId, pagination));
        return sendPaginationDTO;
    }

    public int getTotalSendMessage(Long senderId) {
        return sendMessageDAO.findTotalSendMessage(senderId);
    }

//    public String updateToChecked(Long id) {
//       return receiveMessageDAO.updateToChecked(id);
//    }

    public boolean updateToChecked(Long id) {
        return receiveMessageDAO.updateToChecked(id) > 0;
    }
}
