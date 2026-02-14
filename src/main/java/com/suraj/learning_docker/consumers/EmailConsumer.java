package com.suraj.learning_docker.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.suraj.learning_docker.dtos.user.EmailMessageDto;
import com.suraj.learning_docker.queue.RabbitConfig;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailConsumer {
    private static final Logger logger = LoggerFactory.getLogger(EmailConsumer.class);

    private final JavaMailSender mailSender;

    @RabbitListener(queues = RabbitConfig.EMAIL_QUEUE)
    public void sendEmail(EmailMessageDto msg) {
        try {
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(msg.getTo());
            email.setSubject(msg.getSubject());
            email.setText(msg.getBody());

            mailSender.send(email);
            logger.info("Email sent to {}", msg.getTo());
        } catch (Exception ex) {
            logger.error("Failed to send email to {}: {}", msg.getTo(), ex.getMessage(), ex);
            throw new AmqpRejectAndDontRequeueException("Email delivery failed", ex);
        }
    }
}
