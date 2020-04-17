package com.kuzminski.configuration.core;

import com.kuzminski.configuration.database.DataBaseConfig;
import com.kuzminski.configuration.database.JdbcConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.kuzminski")
@Import({DataBaseConfig.class, JdbcConfig.class})
public class AppConfig {
}
