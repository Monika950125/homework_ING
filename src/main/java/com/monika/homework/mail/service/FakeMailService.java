package com.monika.homework.mail.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FakeMailService implements MailService {

    @Override
    public void send(String content, String title, List<String> recipients) {
        log.info("Sending fake email with title [{}], content [{}] to [{}]", title, content, recipients);
    }
}
