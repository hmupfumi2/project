package com.codevirtus.membershipsystem.auditlibrary.config;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.filter.RequestContextFilter;

/**
 * @author Wilson Chiviti
 */

@Configuration
@Slf4j
public class AuditSpringBeansFactory {

    @Bean
    public TaskExecutor simpleAsyncTaskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }

    @Bean
    public RequestContextFilter requestContextFilter() {
        val requestContextFilter = new RequestContextFilter();
        requestContextFilter.setThreadContextInheritable(true);
        return requestContextFilter;
    }

}
