package io.com.performance.service;

import io.com.performance.domain.UserEvent;
import io.com.performance.enumeration.EventType;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface EventService {
    Collection<UserEvent> getEventsByUserId(Long UserId);
    void addUserEvent(String email, EventType eventType, String device, String ipAddress);
    void addUserEvent(Long UserId, EventType eventType, String device, String ipAddress);
}
