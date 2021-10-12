package com.monika.homework.mail.service;

import java.util.List;

public interface MailService {

    void send(String content, String title, List<String> recipients);
}
