package com.hendisantika.springbootehcache.service;

import com.hendisantika.springbootehcache.model.Company;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-ehcache
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 31/01/18
 * Time: 05.57
 * To change this template use File | Settings | File Templates.
 */
public interface CompanyService {
    Company get(Long id);

    Company get(String name);

    List<Company> getAll();

    void create(Company company);

    Company update(Company company);

    void delete(Long id);

    void delete(Company company);
}
