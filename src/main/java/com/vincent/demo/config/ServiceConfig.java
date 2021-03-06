package com.vincent.demo.config;

import com.vincent.demo.repository.AppUserRepository;
import com.vincent.demo.repository.ProductRepository;
import com.vincent.demo.service.AppUserService;
import com.vincent.demo.service.MailService;
import com.vincent.demo.service.ProductService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ServiceConfig {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ProductService productService(ProductRepository repository,
                                         MailService mailService) {
        return new ProductService(repository, mailService);
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public AppUserService appUserService(AppUserRepository repository) {
        return new AppUserService(repository);
    }

}
