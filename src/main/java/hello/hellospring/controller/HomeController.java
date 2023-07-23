package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String homeLogin(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "home";
        }

        // 로그인된 Member 객체를 Optional<Member>로 가져옵니다.
        Optional<Member> loginMember = (Optional<Member>) session.getAttribute(SessionConst.LOGIN_MEMBER);

        // 로그인에 실패하면 홈으로
        if (!loginMember.isPresent()) {
            return "home";
        }

        // 로그인에 성공하면 logHome으로
        model.addAttribute("member", loginMember.get()); // 이름 속성에만 접근하여 추가
        return "loginHome";
    }
}
