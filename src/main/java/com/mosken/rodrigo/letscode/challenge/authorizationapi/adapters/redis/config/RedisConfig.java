//package com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.redis.config;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.cache.RedisCacheWriter;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.*;
//import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import java.time.Duration;
//
//
////@Configuration
////@EnableRedisRepositories
////public class RedisConfig {
////
////    @Bean
////    public RedisConnectionFactory redisConnectionFactory() {
////        return new JedisConnectionFactory();
////    }
////
////    @Bean
////    public RedisTemplate<?, ?> redisTemplate() {
////        RedisTemplate<?, ?> template = new RedisTemplate<>();
////        template.setConnectionFactory(redisConnectionFactory());
////        return template;
////    }
////}
//@Configuration
////@EnableCaching
//@EnableRedisRepositories
//public class RedisConfig extends CachingConfigurerSupport {
//
//    /**
//     * Select redis as the default caching tool
//     *
//     * @param redisConnectionFactory
//     * @return
//     */
//    /*@Bean
//    //springboot 1.xx
//    public CacheManager cacheManager(RedisTemplate redisTemplate) {
//        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
//        return rcm;
//    }*/
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofHours(1)); //  Set the cache validity to one hour
//        return RedisCacheManager
//                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
//                .cacheDefaults(redisCacheConfiguration).build();
//    }
//
//    /**
//     * Retemplate related configuration
//     *
//     * @param factory
//     * @return
//     */
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
//
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        //Configure connection factory
//        template.setConnectionFactory(factory);
//
//        //Use jackson2jsonredisserializer to serialize and deserialize the value value of redis (JDK serialization is used by default)
//        Jackson2JsonRedisSerializer jacksonSeial = new Jackson2JsonRedisSerializer(Object.class);
//
//        ObjectMapper om = new ObjectMapper();
//        //Specify the fields to be serialized, field, get and set, and modifier range. Any includes private and public
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        //Specify the type of serialized input. The class must be non final modified. Final modified classes, such as string and integer, will run exceptions
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jacksonSeial.setObjectMapper(om);
//
//        //Values are serialized in JSON
//        template.setValueSerializer(jacksonSeial);
//        //Use stringredisserializer to serialize and deserialize the key value of redis
//        template.setKeySerializer(new StringRedisSerializer());
//
//        //Set hash key and value serialization mode
//        template.setHashKeySerializer(new StringRedisSerializer());
//        template.setHashValueSerializer(jacksonSeial);
//        template.afterPropertiesSet();
//
//        return template;
//    }
//
//    /**
//     * Data operation on Hash type
//     *
//     * @param redisTemplate
//     * @return
//     */
//    @Bean
//    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
//        return redisTemplate.opsForHash();
//    }
//
//    /**
//     * Operation on redis string type data
//     *
//     * @param redisTemplate
//     * @return
//     */
//    @Bean
//    public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
//        return redisTemplate.opsForValue();
//    }
//
//    /**
//     * Data operation of linked list type
//     *
//     * @param redisTemplate
//     * @return
//     */
//    @Bean
//    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
//        return redisTemplate.opsForList();
//    }
//
//    /**
//     * Data operations on unordered collection types
//     *
//     * @param redisTemplate
//     * @return
//     */
//    @Bean
//    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
//        return redisTemplate.opsForSet();
//    }
//
//    /**
//     * Data operations on ordered collection types
//     *
//     * @param redisTemplate
//     * @return
//     */
//    @Bean
//    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
//        return redisTemplate.opsForZSet();
//    }
//}