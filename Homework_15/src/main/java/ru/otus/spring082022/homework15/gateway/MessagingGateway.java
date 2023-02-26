package ru.otus.spring082022.homework15.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.messaging.Message;
import ru.otus.spring082022.homework15.domain.Claim;

@org.springframework.integration.annotation.MessagingGateway
public interface MessagingGateway {
    @Gateway(requestChannel = "in-channel")
    public Message<Claim> ProcessClaim(Message<Claim> message);
}
