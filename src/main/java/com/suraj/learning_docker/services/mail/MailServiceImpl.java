package com.suraj.learning_docker.services.mail;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.suraj.learning_docker.dtos.user.EmailMessageDto;
import com.suraj.learning_docker.queue.RabbitConfig;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendMail(String to, String subject, String text) {
        logger.info("Queueing email to {}", to);
        EmailMessageDto msg = new EmailMessageDto();
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setBody(text);

        rabbitTemplate.convertAndSend(RabbitConfig.EMAIL_QUEUE, msg);
    }

}
