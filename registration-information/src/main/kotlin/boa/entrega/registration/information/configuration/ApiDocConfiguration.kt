package boa.entrega.registration.information.configuration

import org.springdoc.core.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

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
