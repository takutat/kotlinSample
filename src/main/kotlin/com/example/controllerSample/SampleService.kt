package com.example.controllerSample

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

@Service
class SampleService {

    suspend fun getSample(): Mono<String> {
        val rand1 = getRandomInternal()
        val rand2 = getRandomInternal()
        val rand3 = getCachedRandom()
        val rand4 = getCachedRandom()

        return Mono.deferContextual { ctx ->
            val key = ctx.getOrDefault("sampleKey", "")
            val userAgent = ctx.get(ReactiveRequestContextHolder.CONTEXT_KEY).headers.getFirst("user-Agent")
            return@deferContextual Mono.just("$rand1 $rand2 $rand3 $rand4 $key $userAgent $ctx")
        }
    }

    suspend fun getRandom(): String {
        val rand1 = getRandomInternal()
        val rand2 = getRandomInternal()
        val rand3 = getCachedRandom()
        val rand4 = getCachedRandom()

        return "$rand1 $rand2 $rand3 $rand4"
    }

    private fun getRandomInternal(): Int = Random(System.nanoTime()).nextInt(10000)

    private suspend fun getCachedRandom(): Int =

        Mono.deferContextual { ctxView ->
            val randMap = ctxView.getOrEmpty<HashMap<String, Int>>("random").get()
            val func: () -> Int = {
                val rand = getRandomInternal()
                randMap["test"] = rand
                rand
            }

            return@deferContextual Mono.just(randMap["test"] ?: func())
        }.awaitSingle()
}