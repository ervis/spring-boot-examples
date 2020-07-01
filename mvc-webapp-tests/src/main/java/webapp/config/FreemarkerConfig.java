package webapp.config;

import org.springframework.boot.info.GitProperties;
import org.springframework.context.annotation.Configuration;

import freemarker.template.TemplateModelException;

@Configuration
public class FreemarkerConfig {
    public FreemarkerConfig(freemarker.template.Configuration configuration,
                            GitProperties gitProperties) throws TemplateModelException {
        configuration.setSharedVariable("serverVersion", gitProperties.get("tags"));
    }
}
