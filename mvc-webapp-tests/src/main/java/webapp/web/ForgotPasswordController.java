package webapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForgotPasswordController {
    @SuppressWarnings("unused")
    private final TranslationService translationService;

    public ForgotPasswordController(TranslationService translationService) {
        this.translationService = translationService;
    }

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "forgot-password";
    }
}
