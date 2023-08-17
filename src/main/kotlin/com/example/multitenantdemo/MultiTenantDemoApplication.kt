package com.example.multitenantdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableJpaAuditing
class MultiTenantDemoApplication

fun main(args: Array<String>) {
    runApplication<MultiTenantDemoApplication>(*args)
}
