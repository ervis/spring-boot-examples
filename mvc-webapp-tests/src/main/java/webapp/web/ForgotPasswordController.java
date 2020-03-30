package webapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ForgotPasswordController {
    @SuppressWarnings("unused")
    private final TranslationService translationService;

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "forgot-password";
    }
}
