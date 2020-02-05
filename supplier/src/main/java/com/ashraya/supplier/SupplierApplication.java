package com.ashraya.supplier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ashraya.supplier.constants.Constants;

@SpringBootApplication
@ComponentScan(basePackages = { "com.ashraya.supplier" })
@EnableJpaRepositories("com.ashraya.supplier.repository")
public class SupplierApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SupplierApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SupplierApplication.class);
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();
        properties.setLocation(new FileSystemResource(Constants.APPLICATION_PROPERTIES_PATH));
        properties.setIgnoreResourceNotFound(false);
        return properties;
    }
}
