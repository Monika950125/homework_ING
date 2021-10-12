package com.monika.homework.customer.service.impl;

import com.monika.homework.customer.domain.Customer;
import com.monika.homework.mail.MailFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.monika.homework.customer.domain.BusinessType.BR_2;
import static com.monika.homework.customer.domain.CustomerType.*;
import static com.monika.homework.mail.domain.MailType.*;

@Component
@RequiredArgsConstructor
public class RiskClassMonitorFacade {

    private final MailFacade mailFacade;

    public void riskClassChanges(Customer customer) {
        if (TYPE_A1 == customer.getType()) {
            mailFacade.send(TYPE_1_CUSTOMER_RISK_CLASS_CHANGED, customer.getCustomerId());
        } else if (TYPE_A5 == customer.getType()) {
            mailFacade.send(TYPE_5_CUSTOMER_RISK_CLASS_CHANGED, customer.getCustomerId());
        } else if (TYPE_A2 == customer.getType() && BR_2 == customer.getBusinessType()) {
            mailFacade.send(TYPE_2_CUSTOMER_RISK_CLASS_CHANGED_BR2, customer.getCustomerId());
        }
    }
}
