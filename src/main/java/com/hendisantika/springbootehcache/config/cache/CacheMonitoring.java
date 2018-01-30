package com.hendisantika.springbootehcache.config.cache;

import com.hendisantika.springbootehcache.config.profile.annotation.Dev;
import net.sf.ehcache.management.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.management.MBeanServer;
import java.lang.management.ManagementFactory;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-ehcache
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 31/01/18
 * Time: 06.04
 * To change this template use File | Settings | File Templates.
 */

@Configuration
@Dev
public class CacheMonitoring {
    @Autowired
    private EhCacheCacheManager ehCacheCacheManager;

    @Bean
    public MBeanServer mBeanServer() {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

        return mBeanServer;
    }

    @Bean
    public ManagementService managementService() {
        ManagementService managementService = new ManagementService(ehCacheCacheManager.getCacheManager(), mBeanServer(), true, true, true, true);
        managementService.init();

        return managementService;
    }
}
