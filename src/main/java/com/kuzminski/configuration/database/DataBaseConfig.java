package com.kuzminski.configuration.database;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import java.util.Objects;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("datasource")
@Getter
@Setter
@Slf4j
public class DataBaseConfig {

    private String driverName;

    private  String password;

    private String url;

    private String initialSize;

    private String username;

    private String maxActive;
    @Bean(value = "dataSource", destroyMethod = "close")
    @Scope("singleton")
    @Primary
    public BasicDataSource getDatasource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverName);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setInitialSize(Integer.valueOf(Objects.requireNonNull(initialSize)));
        dataSource.setUsername(username);
        dataSource.setMaxIdle(Integer.valueOf(Objects.requireNonNull(maxActive)));
        return dataSource;
    }
}
