package com.baixin.ees.web.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;

public class ApplicationEventListener implements ApplicationListener<ApplicationEvent> {

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof ContextClosedEvent) {
			System.out.println("spring容器closed");
		}
		if (event instanceof ContextRefreshedEvent) {
			System.out.println("spring容器Refreshed");
		}
		if (event instanceof ContextStartedEvent) {
			System.out.println("spring容器Started");
		}
		if (event instanceof ContextStoppedEvent) {
			System.out.println("spring容器Stopped");
		}
	}
}
