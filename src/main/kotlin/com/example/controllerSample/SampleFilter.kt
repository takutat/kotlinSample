package com.example.controllerSample

import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono


@Component
class SampleFilter : WebFilter {
    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val request: ServerHttpRequest = exchange.request
        return chain.filter(exchange)
            .contextWrite { it.put("sampleKey", request.id) }
            .contextWrite { it.put(ReactiveRequestContextHolder.CONTEXT_KEY, request) }
            .contextWrite { it.put("random", HashMap<String, Int>()) }
    }
}

object ReactiveRequestContextHolder {
    val CONTEXT_KEY = ServerHttpRequest::class.java
}