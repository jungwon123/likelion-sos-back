package com.example.campus_sos.domain.email;

import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendVerificationEmail(String to, String link) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");

            helper.setTo(to);
            helper.setSubject("캠퍼스 SOS 이메일 인증");
            helper.setText(
                    "<p>다음 링크를 클릭해 이메일 인증을 완료해주세요:</p>" +
                            "<a href=\"" + link + "\">이메일 인증</a>", true);

            helper.setFrom("bugs10613@gmail.com");
            mailSender.send(message);

        } catch (Exception e) {
            System.err.println("===== 이메일 전송 오류 발생 =====");
            System.err.println("에러 클래스: " + e.getClass().getName());
            System.err.println("에러 메시지: " + e.getMessage());
            e.printStackTrace(); // 전체 스택 트레이스
            throw new RuntimeException("예상치 못한 오류 발생", e);
        }
    }

}
