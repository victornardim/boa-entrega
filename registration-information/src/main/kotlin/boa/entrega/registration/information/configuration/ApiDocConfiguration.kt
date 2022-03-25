package boa.entrega.registration.information.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springdoc.core.GroupedOpenApi

@Configuration
class ApiDocConfiguration {
    @Bean
    fun publicApi(): GroupedOpenApi? {
        return GroupedOpenApi.builder()
            .group("registration-information")
            .pathsToMatch("/**")
            .build()
    }
}