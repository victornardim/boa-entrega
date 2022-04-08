package boa.entrega.orders.configuration

import boa.entrega.orders.model.dto.PedidoDto
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import java.time.Duration

@Configuration
@EnableCaching
class CacheConfiguration(
    private val objectMapper: ObjectMapper,
    @Value("\${cache.host}") private val host: String,
    @Value("\${cache.port}") private val port: Int
) : CachingConfigurerSupport() {
    @Bean
    fun redisConnectionFactory(): LettuceConnectionFactory =
        LettuceConnectionFactory(RedisStandaloneConfiguration(host, port))

    @Bean
    @Primary
    fun pedidoCacheManager(
        connectionFactory: RedisConnectionFactory
    ): CacheManager =
        Jackson2JsonRedisSerializer(PedidoDto::class.java).let {
            it.setObjectMapper(objectMapper)
            RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(
                    RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofDays(1))
                ).build()
        }
}
