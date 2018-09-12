package com.bannad927.oocr

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
class OocrApplication

fun main(args: Array<String>) {
     val log = LoggerFactory.getLogger(OocrApplication::class.java)
    log.info("\n******************************************\n***********APPLICATION TO START***********\n******************************************\n\n")
    runApplication<OocrApplication>(*args)
    log.info("\n******************************************\n*******APPLICATION SUCCESS TO START*******\n******************************************\n\n")
}
