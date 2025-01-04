package com.samio.pseudopropertyservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication(scanBasePackages = ["com.ecommercedemo.common", "com.samio.pseudopropertyservice"])
@EntityScan("com.samio.pseudopropertyservice.model", "com.ecommercedemo.common.model")
@EnableJpaRepositories("com.ecommercedemo.common.persistence", "com.samio.pseudopropertyservice.persistence")
@EnableScheduling
class PseudopropertyserviceApplication

fun main(args: Array<String>) {
	runApplication<PseudopropertyserviceApplication>(*args)
}
