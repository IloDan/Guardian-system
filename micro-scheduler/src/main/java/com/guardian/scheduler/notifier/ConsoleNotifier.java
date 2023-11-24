package com.guardian.scheduler.notifier;

import com.guardian.scheduler.clients.AlarmDto;
import com.guardian.scheduler.clients.AlarmRestClient;
import com.guardian.scheduler.clients.OperatorDto;
import com.guardian.scheduler.clients.OperatorRestClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ConsoleNotifier implements Notifier {

    @Override
    public void notify(String contact, String subject, String body){
        log.warn(" Allarme:" + subject + " Problema: " + body + " inviato a " + contact);
    }
}
