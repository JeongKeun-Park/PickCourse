// 2025.02.24 조승찬
package com.app.pickcourse.controller;


import com.app.pickcourse.domain.dto.*;
import com.app.pickcourse.domain.vo.AdminVO;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.domain.vo.NoticeVO;
import com.app.pickcourse.service.AdminService;
import com.app.pickcourse.util.Pagination;
import com.app.pickcourse.util.PaginationOnePage;
import com.app.pickcourse.util.PaginationParticipants;
import com.app.pickcourse.util.Search;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {


    private final AdminService adminService;
    private final HttpSession session;

    // 관리자 메인 페이지
    @GetMapping("/admin")
    public String getAdmin(@SessionAttribute(name = "admin", required = false) AdminVO admin, Model model) {

        if (admin == null) {
            return "redirect:adminLogin";
        }

        return "admin/admin";
    }

    // 회원 관리 목록 조회 25.03.03 조승찬
    @GetMapping("/member-list")
    public String getMemberList(@SessionAttribute(name = "admin", required = false) AdminVO admin,
                                @RequestParam(value = "isAct", required = false) Character isAct,
                                Pagination pagination, Search search, Model model) {

        if (admin == null) {
            return "redirect:adminLogin";
        }

        List<MemberVO> members = adminService.getMemberList(isAct, pagination, search);
        model.addAttribute("members", members);

        return "admin/member-list";
    }

    // 회원 정지 25.03.03 조승찬
    @PostMapping("/member-list-pause")
    public String patchMemberListpause(@SessionAttribute(name = "admin", required = false) AdminVO admin,
                                       @RequestParam("selectedIds") String selectedIds,
                                       @RequestParam(value = "page", required = false) String page,
                                       @RequestParam(value = "type", required = false) String type,
                                       @RequestParam(value = "keyWord", required = false) String keyWord,
                                       @RequestParam(value = "isAct", required = false) String isAct,
                                       RedirectAttributes redirectAttributes) {

        if (admin == null) {
            return "redirect:adminLogin";
        }

        adminService.patchMemberListPause(selectedIds);
        return "redirect:/admin/member-list?page=" +page+"&type="+type+"&keyWord="+keyWord+"&isAct="+isAct;
    }

    // 회원 정지 해제 25.03.03 조승찬
    @PostMapping("/member-list-restart")
    public String patchMemberListRestart(@SessionAttribute(name = "admin", required = false) AdminVO admin,
                                         @RequestParam("selectedIds") String selectedIds,
                                         @RequestParam(value = "page", required = false) String page,
                                         @RequestParam(value = "type", required = false) String type,
                                         @RequestParam(value = "keyWord", required = false) String keyWord,
                                         @RequestParam(value = "isAct", required = false) String isAct,
                                         RedirectAttributes redirectAttributes) {

        if (admin == null) {
            return "redirect:adminLogin";
        }

        adminService.patchMemberListRestart(selectedIds);
        return "redirect:/admin/member-list?page=" +page+"&type="+type+"&keyWord="+keyWord+"&isAct="+isAct;
    }

    // 회원 추방 25.03.03 조승찬
    @PostMapping("/member-list-delete")
    public String deleteMemberList(@SessionAttribute(name = "admin", required = false) AdminVO admin,
                                   @RequestParam("selectedIds") String selectedIds,
                                   @RequestParam(value = "page", required = false) String page,
                                   @RequestParam(value = "type", required = false) String type,
                                   @RequestParam(value = "keyWord", required = false) String keyWord,
                                   @RequestParam(value = "isAct", required = false) String isAct,
                                   RedirectAttributes redirectAttributes) {

        if (admin == null) {
            return "redirect:adminLogin";
        }

        adminService.deleteMemberList(selectedIds);
        return "redirect:/admin/member-list?page=" +page+"&type="+type+"&keyWord="+keyWord+"&isAct="+isAct;
    }

    // 관리자 관리 화면 :: 목록 조회 25.03.03 조승찬
    @GetMapping("/manage-admin-list")
    public String getManageAdminList(@SessionAttribute(name = "admin", required = false) AdminVO admin,
                                     Pagination pagination, Search search, Model model) {

        if (admin == null) {
            return "redirect:adminLogin";
        }

        List<AdminVO> admins = adminService.getManageAdminList(pagination, search);
        model.addAttribute("admins", admins);
        model.addAttribute("admin", new AdminVO());

        return "admin/manage-admin-list";
    }

    // 관리자 관리 화면 :: 등록 25.03.03 조승찬
    @PostMapping("/manage-admin-list")
    public String postManageAdminList(@SessionAttribute(name = "admin", required = false) AdminVO admin,
                                      AdminVO adminVO, RedirectAttributes redirectAttributes) {

        if (admin == null) {
            return "redirect:adminLogin";
        }

        try {
            adminService.postManageAdminList(adminVO);
        } catch (com.app.pickcourse.exception.DuplicateException e){
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/manage-admin-list";
    }

    // 관리자 관리 화면 :: 삭제  25.03.03 조승찬
    @PostMapping("/manage-admin-list-delete")
    public String deleteManageAdminList(@SessionAttribute(name = "admin", required = false) AdminVO admin,
                                        @RequestParam("selectedIds") String selectedIds,
                                        @RequestParam(value = "page", required = false) String page,
                                        @RequestParam(value = "type", required = false) String type,
                                        @RequestParam(value = "keyWord", required = false) String keyWord,
                                        RedirectAttributes redirectAttributes) {

        if (admin == null) {
            return "redirect:adminLogin";
        }

        adminService.deleteManageAdminList(selectedIds);
        return "redirect:/admin/manage-admin-list?page=" + page + "&type=" + type + "&keyWord=" + keyWord;
    }

    // 신규 코스 조회 화면 25.03.07 조승찬
    @GetMapping("/add-course")
    public String getAddCourse(@SessionAttribute(name = "admin", required = false) AdminVO admin,
                               Model model) {

        if (admin == null) {
            return "redirect:adminLogin";
        }

        return "admin/add-course";}

    // 신규 코스 작성  25.03.07 조승찬
    @PostMapping("/add-course")
    public String postAddCourse(@SessionAttribute(name = "admin", required = false) AdminVO admin,
                                CourseDTO courseDTO, Model model) {
        log.info("여기로 들오안오나 ");
        if (admin == null) {
            return "redirect:adminLogin";
        }

        courseDTO.setAdminId(admin.getId());
        adminService.postAddCourse(courseDTO);

        return "redirect:/admin/add-course";
    }

    // 추천 코스 목록 (api) 25.03.08 조승찬
    @GetMapping("/course-list")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getCourseList(@SessionAttribute(name = "admin", required = false) AdminVO admin,
                                                             Pagination pagination, Search search) {

        List<CourseListDTO> list = adminService.getCourseList(pagination,search);

        Map<String, Object> response = new HashMap<String, Object>();
        response.put("courses", list);
        response.put("pagination", pagination);
        response.put("search", search);
        return ResponseEntity.ok(response);
    }

    // 추천코스 목록에서 A ~ D 코스 혹은 봉사 코스로 등록 25.03.09 조승찬
    @PutMapping("/course-list")
    @ResponseBody
    public ResponseEntity<String> patchCourseList(@SessionAttribute(name = "admin", required = false) AdminVO admin,
                                                  @RequestBody Map<String, String> reqData) {

        adminService.patchCourseList(reqData.get("courseId"),reqData.get("courseType").trim());
        // 처리 결과 반환
        return ResponseEntity.ok("Course list fetched successfully!");
    }

    // 추천 코스 조회   25.03.10 조승찬
    @GetMapping("/course-detail/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getCourseDetail(@SessionAttribute(name = "admin", required = false) AdminVO admin,
                                                               @PathVariable("id") Long id) {

        CourseDTO courseDTO = adminService.getCourseDetail(id);
        Map<String,Object> response = new HashMap<String, Object>();
        response.put("course", courseDTO);
        return ResponseEntity.ok(response);
    }

    // 타입별 추천 코스 조회  25.03.12 조승찬
    @GetMapping("/course-type-detail/{courseType}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getCourseTypeDetail(@SessionAttribute(name = "admin", required = false) AdminVO admin,
                                                                   @PathVariable("courseType") String courseType) {
        CourseDTO courseDTO = adminService.getCourseTypeDetail(courseType);
        Map<String,Object> response = new HashMap<String, Object>();
        response.put("course", courseDTO);
        return ResponseEntity.ok(response);
    }

    // 추천 코스 수정  25.03.13 조승찬
    @PutMapping("/course-detail")
    @ResponseBody
    public void putCourseDetail(@SessionAttribute(name = "admin", required = false) AdminVO admin,
                                @RequestBody CourseDTO courseDTO) {
        log.info(courseDTO.toString());
        adminService.putCourseDetail(courseDTO);
    }

    // 추천 코스 삭제  25.03.13 조승찬
    @DeleteMapping("/course-detail/{id}")
    @ResponseBody
    public void deleteCourseDetail(@SessionAttribute(name = "admin", required = false) AdminVO admin,
                                   @PathVariable Long id){
        adminService.deleteCourseDetail(id);
    }

    // 신고 관리 :: 피드 신고, 댓글 신고 25.03.14 조승찬
    @GetMapping("/report-list")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getReportList(@SessionAttribute(name = "admin", required = false) AdminVO admin,
                                                             Pagination pagination, Search search) {
        List<ReportListDTO> list = adminService.getReportList(pagination, search);

        Map<String, Object> response = new HashMap<>();
        response.put("report", list);
        response.put("pagination", pagination);
        response.put("search", search);

        return ResponseEntity.ok(response);
    }

    // 신고된 대상 상세 조회 25.03.14 조승찬
    @GetMapping("/report-detail/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getReportDetail(@SessionAttribute(name = "admin", required = false) AdminVO admin,
                                                               @PathVariable Long id,
                                                               @RequestParam String source) {
        ReplyDetailDTO reply = adminService.getReportDetail(id, source);

        Map<String,Object> response = new HashMap<>();
        response.put("reply", reply);


        return ResponseEntity.ok(response);
    };

    // 공지 사항 목록  2025.03.15 조승찬
    @GetMapping("/notice-list")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getNoticeList(@SessionAttribute(name = "admin", required = false) AdminVO admin,
                                                             Pagination pagination, Search search) {


        List<NoticeListDTO> list = adminService.getNoticeList(pagination, search);

        Map<String,Object> response = new HashMap<>();
        response.put("notice", list);
        response.put("pagination", pagination);
        response.put("search", search);

        return ResponseEntity.ok(response);
    }

    // 공지사항 상세 조회  25.03.15 조승찬
    @GetMapping("/notice-detail/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getNoticeDetail(@SessionAttribute(name = "admin", required = false) AdminVO admin,
                                                               @PathVariable Long id) {

        NoticeVO notice = adminService.getNoticeDetail(id);

        Map<String,Object> response = new HashMap<>();
        response.put("notice", notice);
        return ResponseEntity.ok(response);
    }

    // 공지사항 등록  25.03.15 조승찬
    @PostMapping("/notice-detail")
    @ResponseBody
    public void postNoticeDetail(@SessionAttribute(name = "admin", required = false) AdminVO admin,
                                 @RequestBody NoticeVO notice) {

        notice.setAdminId(admin.getId());
        adminService.postNoticeDetail(notice);
    }

    // 공지사항 수정  25.03.15 조승찬
    @PutMapping("/notice-detail")
    @ResponseBody
    public void putNoticeDetail(@RequestBody NoticeVO notice) {

        adminService.putNoticeDetail(notice);
    }

    // 공지사항 삭제 25.03.15 조승찬
    @DeleteMapping("/notice-detail/{id}")
    @ResponseBody
    public void deleteNoticeDetail(@PathVariable Long id) {

        adminService.deleteNoticeDetail(id);
    }

    // 25.03.25 봉사코스 참여자 명단 조회
    @GetMapping("/participants-list")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getParticipantsList
            (@SessionAttribute(name = "admin", required = false) AdminVO admin,
             @RequestParam Long courseId, PaginationParticipants pagination) {

        List<VolunteerParticipantDTO> participants = adminService.getParticipantsList(courseId, pagination);
        participants.forEach(System.out::println);
        log.info(pagination.toString());

        Map<String,Object> response = new HashMap<>();
        response.put("participants", participants);
        response.put("pagination", pagination);
        return ResponseEntity.ok(response);
    }

//   Admin Login
    @GetMapping("adminLogin")
    public String goToAdminLoginForm(Model model) {
        model.addAttribute("adminVO", new AdminVO());
        return "admin/adminLogin";
    }

    @PostMapping("adminLogin")
    public String adminLogin(@ModelAttribute AdminVO adminVO, RedirectAttributes redirectAttributes) {

        adminVO.setAdminAccount(adminVO.getAdminAccount());
        adminVO.setAdminPassword(adminVO.getAdminPassword());

        Optional<AdminVO> optionalAdmin = adminService.adminLogin(adminVO);

        if (optionalAdmin.isEmpty() ||
                optionalAdmin.get().getAdminPassword() == null ||
                !optionalAdmin.get().getAdminPassword().equals(adminVO.getAdminPassword())) {

            redirectAttributes.addFlashAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "redirect:/admin/adminLogin";
        }

        AdminVO admin = optionalAdmin.get();

        session.setAttribute("admin", admin);

        String redirectUrl = (String) session.getAttribute("redirectAfterLogin");
        if (redirectUrl != null) {
            session.removeAttribute("redirectAfterLogin");
            return "redirect:" + redirectUrl;
        }

        return "redirect:/admin/admin";
    }

    @PostMapping("/logout")
    public String logout() {
        if (session.getAttribute("admin") != null) {
            session.invalidate();
            log.info("관리자 로그아웃 완료");
        } else {
            log.warn("로그아웃 실패: 로그인된 관리자가 없음");
        }
        return "redirect:/admin/adminLogin";
    }

}
