package com.monika.homework.mail.repository;

import com.monika.homework.mail.domain.MailParams;
import com.monika.homework.mail.domain.MailType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MailRepository extends CrudRepository <MailParams, Long> {

    Optional<MailParams> findMailParamsByMailType(MailType type);
}
