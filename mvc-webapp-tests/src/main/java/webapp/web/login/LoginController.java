package webapp.web.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import webapp.web.TranslationService;

@Controller
public class LoginController {
    private final TranslationService translationService;

    public LoginController(TranslationService translationService) {
        this.translationService = translationService;
    }

    @GetMapping("/login")
    public String page(@RequestParam(value = "error", required = false) Boolean error, Model model) {
        if (Boolean.TRUE.equals(error)) {
            String message = translationService.message("authentication.failed");
            model.addAttribute("authenticationErrorMessage", message);
        }
        return "login";
    }
}
