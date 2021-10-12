package com.monika.homework.mail.repository;

import com.monika.homework.mail.domain.MailParams;
import com.monika.homework.mail.domain.MailType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class MailParamsTest {

    @Autowired
    private MailRepository repository;

    @Test
    public void testMailParamsSave(){
        //Given
        MailParams mailParams = new MailParams();

        //When
        repository.save(mailParams);
        Long id = mailParams.getId();
        Optional<MailParams> optionalMailParams = repository.findById(id);

        //Then
        Assertions.assertTrue(optionalMailParams.isPresent());

        //CleanUp
        repository.deleteById(id);
    }

    @Test
    public void testMailParamsDelete(){
        //Given
        MailParams mailParams = new MailParams();
        repository.save(mailParams);
        Long id = mailParams.getId();

        //When
        repository.deleteById(id);
        Optional<MailParams> optionalMailParams = repository.findById(id);

        //Then
        Assertions.assertFalse(optionalMailParams.isPresent());
    }

    @Test
    public void testMailParamsUpdate(){
        //Given
        MailParams mailParams = new MailParams();
        repository.save(mailParams);
        Long id = mailParams.getId();

        //When
        mailParams.setMailType(MailType.TYPE_1_CUSTOMER_RISK_CLASS_CHANGED);
        MailType expectedMailType = MailType.TYPE_1_CUSTOMER_RISK_CLASS_CHANGED;
        Optional<MailParams> optionalMailParams = repository.findById(id);

        //Then
        Assertions.assertEquals(expectedMailType, mailParams.getMailType());

        //CleanUp
        repository.deleteById(id);
    }

}