package integtests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import webapp.web.HomeController;

@WebMvcTest
@ContextConfiguration(classes = HomeController.class)
@WithMockUser
public class HomeControllerIT {
    @Autowired
    private MockMvc mvc;

    @Test
    void validInput_returnsHomePage() throws Exception {
        mvc
                .perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }
}
