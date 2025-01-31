package com.samio.pseudopropertyservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@ComponentScan(
    basePackages = ["com.samio.pseudopropertyservice", "com.ecommercedemo.common"])
@EntityScan(
    "com.samio.pseudopropertyservice.model",
    "com.ecommercedemo.common.model.concretion.permission",
    "com.ecommercedemo.common.model.concretion.permissionuserassociation"
)
@EnableJpaRepositories(
    basePackages = ["com.samio.pseudopropertyservice.persistence", "com.ecommercedemo.common.persistence"])
@EnableScheduling
class PseudopropertyserviceApplication

fun main(args: Array<String>) {
    runApplication<PseudopropertyserviceApplication>(*args)
}
