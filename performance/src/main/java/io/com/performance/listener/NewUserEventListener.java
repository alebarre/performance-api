package io.com.performance.listener;

import io.com.performance.events.NewUserEvent;
import io.com.performance.service.EventService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static io.com.performance.utils.RequestUtils.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class NewUserEventListener {

    private final EventService eventService;
    private final HttpServletRequest request;

    @EventListener
    public void onNewUserEvent (NewUserEvent  event) {
        eventService.addUserEvent(event.getEmail(), event.getType(), getDevice(request), getIpAdress(request));
    }

}
