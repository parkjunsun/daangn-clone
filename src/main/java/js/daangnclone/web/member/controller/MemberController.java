package js.daangnclone.web.member.controller;

import js.daangnclone.domain.member.Member;
import js.daangnclone.service.MemberService;
import js.daangnclone.web.member.dto.CreateMemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String getCreateMemberForm(Model model) {

        model.addAttribute("memberForm", new CreateMemberDto());
        return "member/CreateMemberForm";
    }

    @PostMapping("/signup")
    public String createMember(@ModelAttribute("memberForm") CreateMemberDto createMemberDto) {
        createMemberDto.setPassword(passwordEncoder.encode(createMemberDto.getPassword()));
        memberService.save(createMemberDto.toEntity());
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception, Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "member/LoginForm";
    }

}
