package boa.entrega.registration.information.configuration

import boa.entrega.registration.information.model.dto.ClienteDto
import boa.entrega.registration.information.model.dto.DepositoDto
import boa.entrega.registration.information.model.dto.FornecedorDto
import boa.entrega.registration.information.model.dto.MercadoriaDto
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
    fun clienteCacheManager(
        connectionFactory: RedisConnectionFactory
    ): CacheManager =
        Jackson2JsonRedisSerializer(ClienteDto::class.java).let {
            it.setObjectMapper(objectMapper)
            RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(
                    RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofDays(7))
                ).build()
        }

    @Bean
    fun fornecedorCacheManager(
        connectionFactory: RedisConnectionFactory
    ): CacheManager =
        Jackson2JsonRedisSerializer(FornecedorDto::class.java).let {
            it.setObjectMapper(objectMapper)
            RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(
                    RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofDays(7))
                ).build()
        }

    @Bean
    fun depositoCacheManager(
        connectionFactory: RedisConnectionFactory
    ): CacheManager =
        Jackson2JsonRedisSerializer(DepositoDto::class.java).let {
            it.setObjectMapper(objectMapper)
            RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(
                    RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofDays(7))
                ).build()
        }

    @Bean
    @Primary
    fun mercadoriaCacheManager(
        connectionFactory: RedisConnectionFactory
    ): CacheManager =
        Jackson2JsonRedisSerializer(MercadoriaDto::class.java).let {
            it.setObjectMapper(objectMapper)
            RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(
                    RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofDays(7))
                ).build()
        }
}
