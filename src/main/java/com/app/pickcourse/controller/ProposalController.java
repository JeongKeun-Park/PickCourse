package com.app.pickcourse.controller;

import com.app.pickcourse.domain.dto.*;
import com.app.pickcourse.repository.QuestionDAO;
import com.app.pickcourse.repository.VolunteerParticipantDAO;
import com.app.pickcourse.service.*;
import com.app.pickcourse.util.Pagination;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/proposal")
@RequiredArgsConstructor
@Slf4j
public class ProposalController {
    private final PlanService planService;
    private final HttpSession session;
    private final QuestionDAO questionDAO;
    private final AnswerService answerService;
    private final CourseService courseService;
    private final ParticipantService participantService;
    private final PayService payService;
    private final MemberService memberService;
    private final RealFeedService realFeedService;
    private final WishService wishService;
    private final HttpServletRequest request;


    //    25.03.26 봉사코스 상세 조회   조승찬
    @GetMapping("/eco")
    public String getEco(@SessionAttribute(name = "member", required = false) MemberDTO member,
                         @RequestParam Long id, Model model) {

        Long memberId = null;
        if (member != null) {
            memberId = member.getId();
        }

        // 로그인 후 또는 참가신청 처리 후 돌아올, 현재 url path 보관.
        String redirectURI = request.getRequestURI() + "?id=" + id;
        session.setAttribute("redirectAfterLogin", redirectURI);

        CourseDTO course = courseService.getEco(id);

        model.addAttribute("course", course);
        model.addAttribute("memberId", memberId);

        return "proposal/eco";
    }

    //    25.03.26 참가자 처리   조승찬
    @PostMapping("/eco/participant")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> postEcoparticipant(@RequestParam Long courseId, @RequestParam Long memberId) {
        String message = null;
        message = courseService.postEcoparticipant(courseId, memberId);

        Map<String, Object> reponse = new HashMap<>();
        reponse.put("message", message);
        log.debug("참가자 처리 메세지 :: "+message);
        return ResponseEntity.ok(reponse);
    }

    @GetMapping("/list")
    public String getList(Model model) {
        return "proposal/list";
    }

    @GetMapping("/mobile")
    public String getMobile(Model model) {
        return "proposal/mobile";
    }

    @GetMapping("/modify")
    public String getModify(Model model, Long id) {
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");

        if(loginMember == null) {
            return "redirect:/login/login";
        }else{
            PlanDTO planDTO = planService.getPlanById(id).orElseThrow(()->new RuntimeException());

            model.addAttribute("planDTO", planDTO);
            model.addAttribute("loginMember", loginMember);

            return "proposal/modify";
        }
    }

    @GetMapping("/modifylist")
    public String getModifyList(Model model) {
        MemberDTO loginUser = (MemberDTO) session.getAttribute("member");
        if (loginUser == null) {
            return "redirect:/login/login";
        }else {
        return "proposal/modifylist";
        }
    }

    @GetMapping("/pay")
    public String getPay(Model model, Long planId) {
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");

        if(loginMember == null) {
            return "redirect:/login/login";
        }
        PlanDTO planDTO = planService.getPlanById(planId).orElseThrow();
        planDTO.setMemberId(loginMember.getId());

        log.info("페이 {}", planDTO);

        model.addAttribute("planDTO", planDTO);

        return "proposal/pay";
    }

    @PostMapping("/addKakaoPay")
    public void addKakaoPay(@RequestBody PayDTO payDTO) {
        log.info("들어옴1");

        log.info(payDTO.toString());

        payService.addPay(payDTO);
    }

    @PutMapping("/updatePoint")
    public void update(@RequestBody MemberDTO memberDTO) {
        log.info(memberDTO.toString());
        memberService.updatePoint(memberDTO);
    }

    @PostMapping("/insertParticipant")
    public void insertParticipant(@RequestBody ParticipantDTO participantDTO) {
        log.info("memberId = {}", participantDTO.getMemberId());
        log.info("planId = {}", participantDTO.getPlanId());

        participantService.insertParticipant(participantDTO);
    }

    
    @GetMapping("/read")
    public String getRead(Model model, @RequestParam Long id) {

        MemberDTO loginUser = (MemberDTO) session.getAttribute("member");

        PlanDetailDTO planDetailDTO = planService.getPlanDetailById(id);
        planDetailDTO.setFeedList(realFeedService.getRealFeedList(id));

        // 로그인한 사용자라면 사용자 정보를 추가
        if (loginUser != null) {
            planDetailDTO.setMember(loginUser.toVO());
            boolean isWished = wishService.isWished(loginUser.getId(), id);
            model.addAttribute("isWished", isWished);
        } else {
            // 로그인하지 않았다면 기본값으로 설정
            model.addAttribute("isWished", false);
        }

        int wishCount = wishService.getWishCountByPlanId(id);
        model.addAttribute("wishCount", wishCount);
        model.addAttribute("planDetailDTO", planDetailDTO);
        log.info("planDetailDTO={}",planDetailDTO);

        return "proposal/read";
    }

    @GetMapping("/reviewlist")
    public String getReviewList(Model model, Long planId) {
        FeedListByPlanIdDTO feedListByPlanIdDTO = new FeedListByPlanIdDTO();

        feedListByPlanIdDTO.setPlanId(planId);
        feedListByPlanIdDTO.setFeedLists(realFeedService.getRealFeedList(planId));
        model.addAttribute("lists", feedListByPlanIdDTO);

        return "proposal/reviewlist";
    }

    @GetMapping("/reviewlist/{planId}")
    @ResponseBody
    public PlanByFeedListDTO getReviewList(Pagination pagination, @PathVariable Long planId) {
        return realFeedService.getFeedPagination(pagination, planId);
    }

    @GetMapping("/reviewread")
    public String getReviewRead(Model model) {
        return "proposal/reviewread";
    }

    @GetMapping("/viewlist")
//    사용자가 요청으로 전달한 courseId를 @RequestParam을 통해 받아옵니다.
    public String getVeiwList(Model model, @RequestParam Long courseId) {
//        로그인하면서 session에 추가된 member라는 이름의 정보를 getAttribute로 가져와서 loginUser에 담아준다.
        MemberDTO loginUser = (MemberDTO) session.getAttribute("member");

        CourseSelectDTO course = courseService.findCourseById(courseId);
//      현재 controller로 들어왔을때 course가 제대로 설정됐는지 log.info로 확인
        log.info(course.toString());
//      course와 loginUser를 model객체에 담아서 넘겨준다.
        model.addAttribute("course", course);
        model.addAttribute("loginMember", loginUser);

        return "proposal/viewlist";
    }
    @PostMapping("/viewlist")
    public RedirectView check(Long courseId, RedirectAttributes redirectAttributes) {
        MemberDTO loginUser = (MemberDTO) session.getAttribute("member");
        if(loginUser == null) {
            return new RedirectView("/login/login");
        }
        redirectAttributes.addFlashAttribute("courseId", courseId);

        return new RedirectView("/proposal/write");
    }

    @GetMapping("/write")
    public String getWrite(PlanDTO planDTO, @ModelAttribute("courseId") Long courseId) {
        MemberDTO loginMember = (MemberDTO)session.getAttribute("member");
        if(loginMember == null) {
            return "redirect:/login/login";
        }
        planDTO.setCourseId(courseId);
        return "proposal/write";
    }

    @PostMapping("/write")
    public RedirectView writePlan(PlanDTO planDTO) {
        MemberDTO loginMember = (MemberDTO)session.getAttribute("member");
        if(loginMember == null) {
            return new RedirectView("/login/login");
        }
//      로그인한 member가 작성한거기때문에 memberId는 loginMember.getId()로 설정
        planDTO.setMemberId(loginMember.getId());
//      write HTML에서 전달받은 정보를 insert해준다.
        planService.writePlan(planDTO);
//      이후 modifylist controller로 이동시켜준다.
        return new RedirectView("/proposal/modifylist");
    }

    @GetMapping("/modifylists")
    @ResponseBody
    public MyPLanListDTO getMyPlan(Pagination pagination) {
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");

        Long id = loginMember.getId();
        return planService.getMyPlanList(pagination, id);
    }

    @PostMapping("/writeQuestion")
    public void writeQuestion(@RequestBody QuestionDTO questionDTO) {
        planService.writeQuestion(questionDTO);
    }

    @GetMapping("/read/{planId}")
    @ResponseBody
    public QuestionListDTO getLists(@PathVariable Long planId) {
        return planService.findQuestionLists(planId);
    }

    @PostMapping("/writeAnswer")
    public void writeAnswer(@RequestBody AnswerDTO answerDTO) {
        answerService.answerAdd(answerDTO);
    }

    @GetMapping("/getAnswerLists/{planId}/{questionId}")
    @ResponseBody
    public AnswerDTO getAnswerLists(@PathVariable Long planId,@PathVariable Long questionId) {
        log.info("check");
        return answerService.getAnswerList(planId, questionId);
    }

    @PostMapping("/modifyUpdate")
    public RedirectView modifyPlan(PlanDTO planDTO, RedirectAttributes redirectAttributes) {
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");

        planDTO.setMemberId(loginMember.getId());
        redirectAttributes.addFlashAttribute("planId", planDTO.getId());


        planService.updatePlan(planDTO);

        return new RedirectView("/proposal/modifylist");
    }

    @PutMapping("/update")
    public void updatePlan(@RequestBody PlanDTO planDTO) {
        planService.updatePlan(planDTO);
    }

    @GetMapping("/delete")
    public RedirectView deletePlan(Long id) {
        planService.deletePlan(id);
        return new RedirectView("/proposal/modifylist");
    }


}
