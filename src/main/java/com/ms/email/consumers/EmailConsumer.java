package com.ms.email.consumers;

import com.ms.email.domain.Email;
import com.ms.email.dtos.EmailDTO;
import com.ms.email.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {
    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenEmailQueue(@Payload EmailDTO data){
        var email = new Email();
        BeanUtils.copyProperties(data, email);

        emailService.sendEmail(email);
    }

}
