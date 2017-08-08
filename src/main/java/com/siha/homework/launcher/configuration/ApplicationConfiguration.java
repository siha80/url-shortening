package com.siha.homework.launcher.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.siha.homework"}, excludeFilters = @ComponentScan.Filter(Configuration.class))
public class ApplicationConfiguration {
}
