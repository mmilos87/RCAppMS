package com.rcApp.gatewayService.config;


import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component

@Order(-2)

public class GlobalErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {

    private ServerRequest request;

    public GlobalErrorWebExceptionHandler(GatewayErrorAttributes gea, ApplicationContext applicationContext,

                                          ServerCodecConfigurer serverCodecConfigurer) {

        super(gea, new WebProperties.Resources(), applicationContext);

        super.setMessageWriters(serverCodecConfigurer.getWriters());

        super.setMessageReaders(serverCodecConfigurer.getReaders());

    }

// Rendering html or json

    @Override

    protected RouterFunction<ServerResponse> getRoutingFunction(final ErrorAttributes errorAttributes) {

        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);

    }

    private Mono<ServerResponse> renderErrorResponse(final ServerRequest request) {
        this.request = request;

        final Map<String, Object> errorPropertiesMap = getErrorAttributes(request, ErrorAttributeOptions.defaults());

        return ServerResponse.status(HttpStatus.BAD_REQUEST)

                .contentType(MediaType.APPLICATION_JSON)

                .body(BodyInserters.fromValue(errorPropertiesMap));

    }

}
