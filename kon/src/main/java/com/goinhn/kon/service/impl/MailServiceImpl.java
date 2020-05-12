package com.goinhn.kon.service.impl;

import com.goinhn.kon.service.intf.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author goinhn
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSenderImpl mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendMimeMail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setText(content, true);
            mailSender.send(message);

            log.info("邮件已经发送至：" + to + "， 邮件内容为：" + content);
        } catch (MessagingException e) {
            e.printStackTrace();
            log.error("发送邮件时发生异常！", e);
        }

    }

    @Override
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;

        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setText(content, true);

            FileSystemResource fs = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, fs);

            mailSender.send(message);
        } catch (MessagingException e) {
            log.error("发送邮件时发生异常！", e);
        }
    }
}
