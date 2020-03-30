package integtests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import webapp.security.WebSecurityConfig;
import webapp.web.ForgotPasswordController;
import webapp.web.TranslationService;

@WebMvcTest
@ContextConfiguration(classes = {ForgotPasswordController.class, WebSecurityConfig.class})
public class ForgotPasswordControllerIT {
    @Autowired
    private MockMvc mvc;

    // this can be move to separate test config tha we can add to @ContextConfiguration
    @MockBean
    @SuppressWarnings("unused")
    private TranslationService translationService;

    @Test
    void validInput_returnsForgotPasswordPage() throws Exception {
        mvc
                .perform(get("/forgot-password"))
                .andExpect(status().isOk())
                .andExpect(view().name("forgot-password"));
    }
}
