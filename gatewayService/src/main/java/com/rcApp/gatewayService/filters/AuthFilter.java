package com.rcApp.gatewayService.filters;

import com.google.common.net.HttpHeaders;
import com.rcApp.gatewayService.models.AuthValidateJwtDTO;
import com.rcApp.gatewayService.models.ExceptionDTO;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
/*
* flter za proveru authentikacije i autorizacije
* svaki end point kojin zahteva authentiakaciju i uatorizaciju obeeziti za njim
* onoguceno obelezavanje u skracenom obliku -AuthFilter=rola1,rola2... rolaN
* gde rola predstavlja user rolu za koje je dozvoljeno pristup endpointu
* role moraj biti istog nazivva kao UserRole Velikim slovima
* */

@Log4j2
@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    private final WebClient.Builder webClientBuilder;

    public AuthFilter(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply(Config config) {

        return (exchange, chain) -> {
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                throw new IllegalStateException("Missing authorization information");
            }

            String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            return webClientBuilder.build()
                    .post()
                    .uri("http://authProviderService/api/v1/validateToken")
                    .body(Mono.just(new AuthValidateJwtDTO(token, config.getAllowedRoles())), AuthValidateJwtDTO.class)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
                        Mono<ExceptionDTO> errorMsg = clientResponse.bodyToMono(ExceptionDTO.class);
                        return errorMsg.flatMap(msg -> {
                            throw new IllegalStateException(msg.getErrorMessage());
                        });
                    })
                    .bodyToMono(String.class)
                    .map(refJwt -> {
                        exchange
                                .getResponse()
                                .getHeaders()
                                .add(org.springframework.http.HttpHeaders.AUTHORIZATION, refJwt);
                        return exchange;
                    })
                    .flatMap(chain::filter);
        };
    }

    @Override
    public ShortcutType shortcutType() {
        return ShortcutType.GATHER_LIST;
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("allowedRoles");
    }



    @Data
    @NoArgsConstructor
    public static class Config {
        private List<String> allowedRoles;

    }
}