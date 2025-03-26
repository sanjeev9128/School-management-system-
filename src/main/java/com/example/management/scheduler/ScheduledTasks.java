package com.example.management.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
	@Scheduled(cron = "0 0 12 * * ?") 
	public void scheduleTask() {
		System.out.println("Scheduled task executed at: " + System.currentTimeMillis());
	}

}
