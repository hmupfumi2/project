package com.icap.icap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Hilary on Oct, 2021,
 * Project Name EmployeeTrackingApp
 */


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.icap")
@EntityScan(basePackages = "com.icap")
public class EmployeeTrackingApplication {

    public static void main(String[] args) {

        SpringApplication.run(EmployeeTrackingApplication.class, args);
    }
}
