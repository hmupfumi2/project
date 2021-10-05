package com.icap.icap.commons.utilities.beans;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;


@Component
public class EventPublisherWrapper implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishEvent(Object event){
        applicationEventPublisher.publishEvent(event);
    }

    public void publishEvent(ApplicationEvent event){
        applicationEventPublisher.publishEvent(event);
    }

}
