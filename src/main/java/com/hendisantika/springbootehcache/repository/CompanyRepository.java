package com.hendisantika.springbootehcache.repository;

import com.hendisantika.springbootehcache.model.Company;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-ehcache
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 31/01/18
 * Time: 05.54
 * To change this template use File | Settings | File Templates.
 */
public interface CompanyRepository {
    Company find(Long id);

    Company find(String name);

    List<Company> findAll();

    void create(Company company);

    Company update(Company company);

    void delete(Long id);

    void delete(Company company);
}
