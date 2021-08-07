package com.example.playgroundroki.mail.helper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.util.Map;

import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;

/**
 * 메일 송신 헬퍼
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class SendMailHelper {

    // todo: 현재 JavaMailSender가 null 인 상태.
    private final JavaMailSender javaMailSender;

    /**
     * 메일을 송신한다.
     * @param toAddress
     * @param subject
     * @param body
     */
    public void sendMail(String[] toAddress, String subject, String body) {
        var message = new SimpleMailMessage();
        message.setTo(toAddress);
        message.setSubject(subject);
        message.setText(body);

        try {
            javaMailSender.send(message);
        } catch (MailException e) {
            log.error("failed to send mail", e);
            throw e;
        }
    }

    // template (타임리프와 같은 템플릿으로 보낼려면 아래와 같은 방식 혹은 Mime 뭐시기 방식 써야하는 것 같다).
//    public String getMailBody(String template, Map<String, Object> objects) {
//        var templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver());
//
//        var context = new Context();
//        if (isNotEmpty(objects)) {
//            objects.forEach(context::setVariable);
//        }
//
//        return templateEngine.process(template, context);
//    }
//
//    protected ITemplateResolver templateResolver() { // 예제에서 접근제한자를 protected로 설정하는 이유가 있을까?
//        StringTemplateResolver resolver = new StringTemplateResolver();
//        resolver.setTemplateMode("TEXT");
//        resolver.setCacheable(false); // 안전을 위해 캐시하지 않는다.
//        return resolver;
//    }

}
