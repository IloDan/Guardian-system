package com.guardian.scheduler.notifier;

import com.guardian.scheduler.smtp.EmailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class SmtpNotifier implements Notifier{

    @Autowired
    private EmailService emailService;

    @Override
    public void notify(String to, String subject, String text){
        emailService.sendMail(to, subject, text);
    }
}
