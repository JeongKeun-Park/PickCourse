package com.app.pickcourse.controller;


import com.app.pickcourse.domain.dto.*;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.domain.vo.WishVO;
import com.app.pickcourse.repository.*;
import com.app.pickcourse.service.*;
import com.app.pickcourse.util.Pagination;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/my-page/*")
@RequiredArgsConstructor
@Slf4j
public class MyPageController {
    private final MemberDAO memberDAO;
    private final MessageService messageService;
    private final SendMessageDAO sendMessageDAO;
    private final ReceiveMessageDAO receiveMessageDAO;
    private final MemberService memberService;
    private final MemberVO memberVO;
    private final HttpSession session;
    private final SendMessageFileDAO sendMessageFileDAO;
    private final ReceiveMessageFileDAO receiveMessageFileDAO;
    private final ParticipantService participantService;
    private final HttpServletResponse response;
    private final FileService fileService;
    private final HttpServletRequest request;
    private final MyFeedService myFeedService;
    private final MyReplyService myReplyService;
    private final PayService payService;
    private final PlanService planService;
    private final WishService wishService;

    @GetMapping("changePassword")
    public String getChangePassword(){
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }
        return "my-page/changePassword";
    }
    @PostMapping("/changePassword")
    public String changePassword(@RequestParam String oldPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String newPasswordConfirm,
                                 RedirectAttributes redirectAttributes) {
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        boolean isCorrectPassword = memberService.checkPassword(member.getId(), oldPassword);
        if (!isCorrectPassword) {
            redirectAttributes.addFlashAttribute("error", "기존 비밀번호가 올바르지 않습니다.");
            return "redirect:/my-page/changePassword";
        }

        if (!newPassword.equals(newPasswordConfirm)) {
            redirectAttributes.addFlashAttribute("error", "새 비밀번호가 일치하지 않습니다.");
            return "redirect:/my-page/changePassword";
        }

        memberService.updatePassword(member.getId(), newPassword);
        session.invalidate();

        redirectAttributes.addFlashAttribute("success", "비밀번호 변경이 완료되었습니다. 새로운 비밀번호로 다시 로그인해주세요.");

        return "redirect:/login/login";
    }




    @GetMapping("/messageList_Send")
    public String goToSendListPage(@SessionAttribute(name = "member", required = false) MemberDTO member) {
//        MemberDTO member = (MemberDTO) session.getAttribute("member");

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        return "my-page/messageList_Send";
    }

    @GetMapping("/messageList_Sends")
    @ResponseBody
    public SendPaginationDTO getSendMessages(Pagination pagination, @SessionAttribute(name = "member", required = false) MemberDTO member, HttpServletResponse response)  throws IOException {
//        MemberDTO member = (MemberDTO) session.getAttribute("member");
        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            response.sendRedirect("/login/login");
            return null;
        }

        Long senderId = member.getId();

        return messageService.getSendList(senderId, pagination);
    }




    @GetMapping("/messageListReceive")
    public String goToReceiveListPage(@SessionAttribute(name = "member", required = false) MemberDTO member) {
//        MemberDTO member = (MemberDTO) session.getAttribute("member");

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        return "my-page/messageListReceive";
    }

    @GetMapping("/messageListReceives")
    @ResponseBody
    public ReceivePaginationDTO getReceivedMessages(Pagination pagination, @SessionAttribute(name = "member", required = false) MemberDTO member)  throws IOException {
//        MemberDTO member = (MemberDTO) session.getAttribute("member");
        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            response.sendRedirect("/login/login");
            return null;
        }

        Long receiverId = member.getId();

        return messageService.getReceiveList(receiverId, pagination);
    }

    @PostMapping("/readMessage")
    public ResponseEntity<Boolean> markAsRead(@RequestParam Long id) {
        Boolean updatedStatus = messageService.updateToChecked(id);
        return ResponseEntity.ok(updatedStatus);
    }


    @PostMapping("/deleteReceiveMessage")
    @ResponseBody
    public boolean deleteMessage(@RequestParam Long id) {
        MemberDTO member = (MemberDTO) session.getAttribute("member");

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return false;
        }
        return messageService.deleteReceiveMessageById(id);
    }

    @PostMapping("/deleteSendMessage")
    @ResponseBody
    public boolean deleteSendMessage(@RequestParam Long id) {
        MemberDTO member = (MemberDTO) session.getAttribute("member");

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return false;
        }
        return messageService.deleteSendMessageById(id);
    }



    @GetMapping("/my-page/messageWrite")
    public String showMessageWritePage(
            @RequestParam(value = "receiver", required = false, defaultValue = "") String receiverEmail,
            Model model
    ) {
        SendMessageDTO sendMessageDTO = new SendMessageDTO();

        MemberDTO member = (MemberDTO) session.getAttribute("member");

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        if (!receiverEmail.isEmpty()) {
            sendMessageDTO.setReceiverEmail(receiverEmail);
        }

        model.addAttribute("sendMessageDTO", sendMessageDTO);
        return "my-page/messageWrite";
    }

    @PostMapping("/my-page/messageWrite")
    public String sendMessage(
            @ModelAttribute("sendMessageDTO") SendMessageDTO sendMessageDTO,
            @RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes
    ) {
        if (file == null || file.isEmpty()) {
            log.warn("파일이 비어있습니다.");
        } else {
            log.info("파일 이름: {}", file.getOriginalFilename());
        }

        MemberDTO member = (MemberDTO) session.getAttribute("member");

        if (member == null) {
            return "redirect:/login/login";
        }

        if (sendMessageDTO.getReceiverEmail() == null || sendMessageDTO.getReceiverEmail().trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "받는 사람을 입력해주세요.");
            return "redirect:/my-page/messageWrite";
        }

        Optional<Long> receiverId = memberDAO.findIdByEmail(sendMessageDTO.getReceiverEmail());
        if (receiverId.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "받는 사람 이메일을 찾을 수 없습니다.");
            return "redirect:/my-page/messageWrite";
        }

        sendMessageDTO.setReceiverId(receiverId.get());
        sendMessageDTO.setSenderId(member.getId());

        try {
            messageService.sendMessage(sendMessageDTO, file);
            redirectAttributes.addFlashAttribute("success", "메시지가 성공적으로 전송되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "메시지 전송 중 오류가 발생했습니다.");
        }

        return "redirect:/my-page/messageList_Send";
    }

    @GetMapping("/payContents")
    @ResponseBody
    public List<MyPayListDTO> getMyPaymentList(@SessionAttribute(name = "member", required = false) MemberDTO member) {
        if (member == null) {
            return Collections.emptyList();
        }

        Long memberId = member.getId();
        return payService.getMyPayList(memberId);
    }


    @GetMapping("myCourse")
    public void getMyCourse(){}

    @GetMapping("myMain")
    public String getMyMain(Model model) {
        MemberDTO member = (MemberDTO) session.getAttribute("member");

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        MemberDTO memberDTO = memberService.getMemberById(member.getId());
        model.addAttribute("memberDTO", memberDTO);

        return "my-page/myMain";
    }

    @GetMapping("/myPageModify")
    public String getMyPageModify(Model model) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");

        if (memberDTO == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        model.addAttribute("memberDTO", memberDTO);
        return "my-page/myPageModify";
    }

    @PostMapping("/myPageModify")
    public String updateProfile(@ModelAttribute MemberDTO memberDTO, Model model, RedirectAttributes redirectAttributes) {

        MemberDTO currentMember = (MemberDTO) session.getAttribute("member");
        String memberNickname = currentMember.getMemberNickname();

        String newNickname = memberDTO.getMemberNickname();

        if (!memberNickname.equals(newNickname)) {
            boolean isDuplicate = memberService.checkNicknameDuplicate(newNickname);
            if (isDuplicate) {
                model.addAttribute("nicknameError", "이미 사용 중인 닉네임입니다.");
                return "my-page/myPageModify";
            }
        }
        String birth = memberDTO.getMemberBirth();
        if (!birth.matches("^(19|20)\\d{2}(\\.?(0[1-9]|1[0-2])\\.?(0[1-9]|[12][0-9]|3[01]))$")) {
            model.addAttribute("birthError", "생년월일은 YYYY.MM.DD 또는 YYYYMMDD 형식으로 입력해주세요.");
            return "my-page/myPageModify";
        }
        memberService.update(memberDTO);
        redirectAttributes.addFlashAttribute("successMessage", "회원 정보가 성공적으로 수정되었습니다.");
        return "redirect:/my-page/myPageModify";
    }






    @GetMapping("myReply")
    public void getMyReply(){}
    @GetMapping("payContent")
    public String getPayContent(){
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");

        if (memberDTO == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }
        return "my-page/payContent";
    }

    @GetMapping("userQuit")
    public String getUserQuitForm(){
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");

        if (memberDTO == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }
        return "my-page/userQuit";
    }
    @PostMapping("userQuit")
    public String getUserQuit() {

        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");


        if (memberDTO == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        Long id = memberDTO.getId();
        log.info(id.toString());
        memberService.delete(id);
        session.invalidate();

        return "redirect:/";
    }

    @GetMapping("/getEmailByNickname")
    public ResponseEntity<MemberDTO> getEmailByNickname(@RequestParam String memberNickname) {
        return memberService.findEmailByNickname(memberNickname)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @GetMapping("/files/send/{sendMessageId}")
    @ResponseBody
    public SendMessageFileDTO getSendMessageFile(@PathVariable Long sendMessageId) {

        SendMessageFileDTO file = sendMessageFileDAO.selectBySendMessageId(sendMessageId);

        return file;
    }

    @GetMapping("/files/receive/{receiveMessageId}")
    @ResponseBody
    public ReceiveMessageFileDTO getReceiveMessageFile(@PathVariable Long receiveMessageId) {

        ReceiveMessageFileDTO file = receiveMessageFileDAO.selectByReceiveMessageId(receiveMessageId);

        return file;
    }

    @GetMapping("/normalCourseParticipationCount")
    public ResponseEntity<Integer> getNormalCourseParticipationCount(@SessionAttribute(name = "member") MemberDTO member) {
        Long memberId = member.getId();
        int count = participantService.getNormalCourseParticipationCount(memberId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/volunteerCourseParticipationCount")
    public ResponseEntity<Integer> getVolunteerCourseParticipationCount(@SessionAttribute(name = "member") MemberDTO member) {
        Long memberId = member.getId();
        int count = participantService.getVolunteerCourseParticipationCount(memberId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/myFeedCount")
    public ResponseEntity<Integer> getMyFeedCount(@SessionAttribute(name = "member") MemberDTO member) {
        Long memberId = member.getId();
        int count = myFeedService.getMyFeedCount(memberId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/myReplyCount")
    public ResponseEntity<Integer> getMyReplyCount(@SessionAttribute(name = "member") MemberDTO member) {
        Long memberId = member.getId();
        int count = myReplyService.getMyReplyCount(memberId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/myReviewCount")
    public ResponseEntity<Integer> getMyReviewCount(@SessionAttribute(name = "member") MemberDTO member) {
        Long memberId = member.getId();
        int count = myFeedService.getMyReviewCount(memberId);
        return ResponseEntity.ok(count);
    }


    @GetMapping("/recentCourse")
    public ResponseEntity<RecentCourseDTO> getRecentCourse(@SessionAttribute(name = "member", required = false) MemberDTO member) {
        if (member == null || member.getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        RecentCourseDTO recentCourse = participantService.getRecentCourse(member.getId());
        return ResponseEntity.ok(recentCourse);
    }

    @GetMapping("/my-courses")
    @ResponseBody
    public ResponseEntity<List<RecentCourseDTO>> getMyCourses(@SessionAttribute(name = "member", required = false) MemberDTO member) {
        if (member == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 또는 .badRequest().build();
        }

        List<RecentCourseDTO> myCourses = participantService.getMyCourses(member.getId());
        return ResponseEntity.ok(myCourses);
    }

    @GetMapping("/heart")
    public String getHeart(@SessionAttribute(name = "member", required = false) MemberDTO member,
                           HttpServletRequest request, Model model) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        } else {
            Long memberId = member.getId();
            model.addAttribute("memberId", memberId);
        }

        return "my-page/heart";
    }

    @GetMapping("/wishList")
    @ResponseBody
    public WishPaginationDTO getWishList(Pagination pagination, @SessionAttribute(name = "member", required = false) MemberDTO member) throws IOException {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            response.sendRedirect("/login/login");
            return null;
        }

        return wishService.getWishList(member.getId(), pagination);
    }



    @GetMapping("/recruit")
    public String getMyRecruitPlans(@SessionAttribute(name = "member", required = false) MemberDTO member, Model model,
                                    @RequestParam(name = "offset", defaultValue = "0") int offset,
                                    @RequestParam(name = "limit", defaultValue = "5") int limit) {

        if (member == null) {
            session.setAttribute("redirectAfterLogin", request.getRequestURI());
            return "redirect:/login/login";
        }

        Long memberId = member.getId();

        List<RecruitPlanDTO> recruitPlans = planService.getMyRecruitPlans(memberId, offset, limit);
        model.addAttribute("recruitPlans", recruitPlans);
        return "my-page/recruit";
    }

    @GetMapping("/recruit/load")
    @ResponseBody
    public List<RecruitPlanDTO> loadMoreRecruitPlans(@SessionAttribute(name = "member", required = false) MemberDTO member,
                                                     @RequestParam(name = "offset") int offset,
                                                     @RequestParam(name = "limit") int limit) {
        if (member == null) {
            return Collections.emptyList();
        }

        return planService.getMyRecruitPlans(member.getId(), offset, limit);
    }


    @GetMapping("/recruit/{planId}/applicants")
    @ResponseBody
    public List<ApplicantDTO> getApplicants(@PathVariable("planId") Long planId) {
        return participantService.getApplicants(planId);
    }


    @GetMapping("/recentFeeds")
    @ResponseBody
    public ResponseEntity<List<Map<String, Object>>> getRecentFeeds(@RequestParam Long memberId) {
        List<Map<String, Object>> recentFeeds = myFeedService.getRecentFeeds(memberId);
        return ResponseEntity.ok(recentFeeds);
    }


    @PostMapping("upload")
    @ResponseBody
    public ResponseEntity<Map<String, String>> upload(@RequestParam("file") MultipartFile file, @SessionAttribute(name = "member", required = false) MemberDTO member) {
        Long memberId = member.getId();

        if(memberId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        MemberDTO updatedMember = memberService.uploadProfile(memberId, file);

        if(updatedMember == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        session.setAttribute("member", updatedMember);

        Map<String, String> result = new HashMap<>();
        result.put("filePath", updatedMember.getMemberFilePath());
        result.put("fileName", updatedMember.getMemberFileName());

        return ResponseEntity.ok(result);
    }

    @PostMapping("/deleteHeart")
    public ResponseEntity<String> deleteWish(@RequestBody WishVO wishVO) {
        wishService.removeWish(wishVO.getMemberId(), wishVO.getPlanId());
        return ResponseEntity.ok("찜 삭제 완료");
    }

    @PostMapping("/insertHeart")
    public ResponseEntity<String> addWish(@RequestBody WishVO wishVO) {
        wishService.addWish(wishVO.getMemberId(), wishVO.getPlanId());
        return ResponseEntity.ok("찜 추가 완료");
    }

}
