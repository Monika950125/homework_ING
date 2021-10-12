package com.monika.homework.mail;

import com.monika.homework.mail.domain.MailType;
import com.monika.homework.mail.repository.MailRepository;
import com.monika.homework.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class MailFacade {

    private final MailService mailService;
    private final MailRepository mailRepository;

    public void send(MailType type, Long customerId) {
        mailRepository.findMailParamsByMailType(type)
                .ifPresent(mailParams ->
                        mailService.send(
                                mailParams.getContent() + customerId,
                                mailParams.getTitle(),
                                Arrays.asList(mailParams.getRecipients().split(","))));
    }
}
