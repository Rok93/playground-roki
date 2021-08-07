package com.example.playgroundroki;

import com.example.playgroundroki.mail.helper.SendMailHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final SendMailHelper sendMailHelper;

    @Override
    public void run(String... args) throws Exception {
        if (sendMailHelper == null) {
            System.out.println("sendMailHelper는 null입니다!");
        }
        sendMailHelper.sendMail(
                new String[]{"printf1004@naver.com"},
                "Java Mail Sender Test",
                "공부하세여!"
        );
    }
}
