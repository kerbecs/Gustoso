package com.project.restaurant.adapters.rabbit;

import com.project.restaurant.domain.entity.ConfirmationToken;
import com.project.restaurant.domain.entity.User;
import com.project.restaurant.domain.service.ConfirmationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserConfirmEmailListener {
    private final JavaMailSender javaMailSender;
    private final ConfirmationTokenService tokenService;

    @RabbitListener(queues = "${spring.rabbitmq.queue1.name}")
    public void createToken(User user) {
        ConfirmationToken confirmationToken = ConfirmationToken.builder()
                .confirmationToken(UUID.randomUUID().toString())
                .expireDate(LocalDateTime.now().plus(1, TimeUnit.HOURS.toChronoUnit()))
                .user(user)
                .build();

        tokenService.saveConfirmationToken(confirmationToken);

        sendMessage(confirmationToken);


    }

    private void sendMessage(ConfirmationToken confirmationToken) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(confirmationToken.getUser().getUserDescription().getEmail());
        simpleMailMessage.setSubject("Email confirmation");
        simpleMailMessage.setText(String.format("Hi! Please confirm your email by clicking on the following link: " +
                "\nhttp://localhost:8080/confirm-email?id=%s", confirmationToken.getConfirmationToken()));

        javaMailSender.send(simpleMailMessage);
    }

}
