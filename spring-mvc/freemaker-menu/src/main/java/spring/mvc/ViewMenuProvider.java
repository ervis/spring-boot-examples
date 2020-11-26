package spring.mvc;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import spring.mvc.data.TopLevelMenuItem;

@Slf4j
public class ViewMenuProvider extends HandlerInterceptorAdapter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @SneakyThrows
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) {
        // here we add the menu object that will be used by freemarker
        // to generate the HTML
        if (modelAndView == null || modelAndView.getModelMap().getAttribute("menu") != null) {
            return;
        }
        URL resource = getClass().getClassLoader().getResource("menu.json");
        if (resource == null) {
            return;
        }
        String menuJson = Files.readString(Paths.get(resource.toURI()));

        TypeReference<Map<String, Map<String, TopLevelMenuItem>>> ref = new TypeReference<>() {
        };
        Map<String, Map<String, TopLevelMenuItem>> menu = objectMapper.readValue(menuJson, ref);

        Optional.ofNullable(modelAndView).ifPresent(mav -> mav.addObject("menu", menu));
    }
}
