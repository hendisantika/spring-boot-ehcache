package com.hendisantika.springbootehcache.config.cache;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-ehcache
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 31/01/18
 * Time: 06.03
 * To change this template use File | Settings | File Templates.
 */

@Configuration
@EnableCaching
public class CacheConfiguration {
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactory() {
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        cacheManagerFactoryBean.setShared(true);

        return cacheManagerFactoryBean;
    }

    @Bean
    public EhCacheCacheManager ehCacheCacheManager() {
        EhCacheCacheManager cacheManager = new EhCacheCacheManager();
        cacheManager.setCacheManager(ehCacheManagerFactory().getObject());
        cacheManager.setTransactionAware(true);

        return cacheManager;
    }
}
