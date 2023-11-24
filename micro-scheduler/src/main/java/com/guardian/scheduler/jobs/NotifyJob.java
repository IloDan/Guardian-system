package com.guardian.scheduler.jobs;

import com.guardian.scheduler.clients.*;
import com.guardian.scheduler.notifier.ConsoleNotifier;
import com.guardian.scheduler.notifier.Notifier;
import com.guardian.scheduler.notifier.SmtpNotifier;
import lombok.extern.log4j.Log4j2;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author luca_
 */
@Log4j2
public class NotifyJob implements Job {

    /*@Autowired
    AlarmRestClient alarmRestClient;
    @Autowired
    OperatorRestClient operatorRestClient;*/

    @Autowired
    AppRestClient appRestClient;
    @Autowired
    Notifier[] notifier;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            for (AlarmDto ad : appRestClient.getAlarmsNotSent()) {
                for (OperatorDto op : appRestClient.getActive()) {
                    for(int i=0; i<notifier.length; ++i){
                        notifier[i].notify(op.getContact(), ad.getSubject(), ad.getBody());
                    }
                }
                appRestClient.setDispatched(ad.getId());
            }
        }catch (Exception e){
            log.error(e);
        }
    }
}
