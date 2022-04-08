package boa.entrega.orders.configuration

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.context.annotation.Configuration
import javax.servlet.http.HttpServletRequest

@Configuration
class ClientAuthConfiguration(
    private val request: HttpServletRequest
) : RequestInterceptor {
    companion object {
        private const val AUTHORIZATION_HEADER = "Authorization"
    }

    override fun apply(requestTemplate: RequestTemplate) {
        requestTemplate.header(AUTHORIZATION_HEADER, request.getHeader(AUTHORIZATION_HEADER))
    }
}
