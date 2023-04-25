package com.example.controllerSample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.server.WebFilter

@SpringBootApplication
@ConfigurationPropertiesScan
class ControllerSampleApplication

fun main(args: Array<String>) {
	runApplication<ControllerSampleApplication>(*args)
}
