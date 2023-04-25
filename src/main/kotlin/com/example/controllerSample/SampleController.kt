package com.example.controllerSample

import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.runBlocking
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono


@RestController
class SampleController(
    val sampleService: SampleService,
) {

    @RequestMapping("/sample")
    fun getSample(): Mono<String> = runBlocking {
        return@runBlocking sampleService.getSample()
    }

    @RequestMapping("/sample2")
    suspend fun getSample2(): String {
        return sampleService.getSample().awaitSingle()
    }

    @GetMapping("/input")
    suspend fun getInput(): String {
        return sampleService.getRandom()
    }
}