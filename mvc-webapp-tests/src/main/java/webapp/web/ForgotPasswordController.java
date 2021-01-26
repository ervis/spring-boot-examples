package webapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class ForgotPasswordController {
    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "forgot-password";
    }
}
