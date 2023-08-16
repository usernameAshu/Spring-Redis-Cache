package com.ibm.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringProductCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringProductCacheApplication.class, args);
	}

	/*
	 * @Bean(name = "RedisCacheManager") public CacheManager getManager() {
	 * JdkSerializationRedisSerializer contextAwareRedisSerializer = new
	 * JdkSerializationRedisSerializer( getClass().getClassLoader());
	 * 
	 * RedisCacheConfiguration redisCacheConfiguration =
	 * RedisCacheConfiguration.defaultCacheConfig() .disableCachingNullValues() //
	 * .entryTtl(Duration.ofHours(12) .serializeValuesWith(
	 * RedisSerializationContext.SerializationPair.fromSerializer(
	 * contextAwareRedisSerializer));
	 * 
	 * return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(
	 * redisConnectionFactory()) .cacheDefaults(redisCacheConfiguration).build();
	 * 
	 * }
	 * 
	 * @Bean public RedisConnectionFactory redisConnectionFactory() {
	 * JedisConnectionFactory factory = new JedisConnectionFactory();
	 * factory.setHostName("localhost"); // Replace with your Redis server host
	 * factory.setPort(6379); // Replace with your Redis server port return factory;
	 * }
	 * 
	 * @Bean public RedisTemplate<String, Object> redisTemplate() { final
	 * RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
	 * template.setKeySerializer(new StringRedisSerializer());
	 * template.setHashKeySerializer(new StringRedisSerializer());
	 * template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
	 * template.setConnectionFactory(redisConnectionFactory()); return template; }
	 */

}