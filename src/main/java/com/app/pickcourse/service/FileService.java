package com.app.pickcourse.service;

import com.app.pickcourse.domain.vo.FileVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileService {

    //    파일 업로드
    public FileVO upload(MultipartFile file){
        if(file.getOriginalFilename().equals("")){
            return null;
        }
        String todayPath = getPath();
        String rootPath = "/upload/" + todayPath;
        String fileName = null;
        UUID uuid = UUID.randomUUID();

        try {
            File directory = new File(rootPath);
            if(!directory.exists()){
                directory.mkdirs();
            }

            file.transferTo(new File(rootPath, uuid.toString() + "_" + file.getOriginalFilename()));

//            썸네일 가공
            if(file.getContentType().startsWith("image")){
                fileName = "t_" + uuid.toString() + "_" + file.getOriginalFilename();
                FileOutputStream out = new FileOutputStream(new File(rootPath, fileName));
                Thumbnailator.createThumbnail(file.getInputStream(), out, 1300,350);
                out.close();

                FileVO fileVO = new FileVO();  // 객체 생성
                fileVO.setFilePath(todayPath);
                fileVO.setFileName(fileName);
                fileVO.setFileSize(String.valueOf(file.getSize()));
                return fileVO;                  // 객체 반환
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private String getPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
}
