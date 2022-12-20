package com.moneycare

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.ComponentScan


//@SpringBootApplication(exclude = [org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration::class])
@SpringBootApplication
@ComponentScan(
    basePackages = ["com.moneycare"]
)
@EnableFeignClients(basePackages = ["com.moneycare"])
open class Application : CommandLineRunner {
    private val log = LoggerFactory.getLogger(javaClass)


    override fun run(vararg args: String?) {
        log.info("app starts...")
    }

}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
