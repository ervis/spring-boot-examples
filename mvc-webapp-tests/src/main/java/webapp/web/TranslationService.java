package webapp.web;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TranslationService {
    private final MessageSource messageSource;

    public TranslationService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String message(String key, String... args) {
        return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }
}
