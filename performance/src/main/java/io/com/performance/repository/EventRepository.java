package io.com.performance.repository;

import io.com.performance.domain.UserEvent;
import io.com.performance.enumeration.EventType;

import java.util.Collection;

public interface EventRepository {

    Collection<UserEvent> getEventsByUserId(Long UserId);
    void addUserEvent(String email, EventType eventType, String device, String ipAddress);
    void addUserEvent(Long UserId, EventType eventType, String device, String ipAddress);

}
