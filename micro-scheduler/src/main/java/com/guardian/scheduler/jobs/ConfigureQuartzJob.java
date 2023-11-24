package com.guardian.scheduler.jobs;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author luca_
 */
@Configuration
public class ConfigureQuartzJob {
    @Bean
    public JobDetail jobSmoke() {
        return JobBuilder.newJob(NotifyJob.class).withIdentity(NotifyJob.class.getName()).storeDurably().build();
    }

    @Bean
    public Trigger triggerSmoke(JobDetail details) {
        return TriggerBuilder.newTrigger()
                .forJob(details)
                .withIdentity(String.format("Trigger%s", NotifyJob.class.getName()))
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * ? * * *"))
                .build();
    }
}