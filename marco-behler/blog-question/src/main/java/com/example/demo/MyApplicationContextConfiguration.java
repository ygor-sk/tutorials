package com.example.demo;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MyApplicationContextConfiguration {  // (1)

    @Bean
    public UserDao userDao() { // (3)
        System.out.println("### MyApplicationContextConfiguration.userDao");
        return new UserDao(dataSource());
    }

    @Bean
    public DataSource dataSource() {  // (2)
        System.out.println("### MyApplicationContextConfiguration.dataSource");
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            if (stackTraceElement.getClassName().startsWith("com.example.demo")) {
                System.out.println("##### " + stackTraceElement);
            } else {
                System.out.println("##### ...");
            }
        }
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("s3cr3t");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/myDatabase");
        return dataSource;
    }

}
