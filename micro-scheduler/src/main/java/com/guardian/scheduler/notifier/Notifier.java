package com.guardian.scheduler.notifier;


public interface Notifier {
    void notify(String to, String subject, String text);
}
