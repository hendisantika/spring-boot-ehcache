package com.hendisantika.springbootehcache.service;

import com.hendisantika.springbootehcache.model.Company;
import com.hendisantika.springbootehcache.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    @Transactional(readOnly = true)
    public Company get(Long id) {
        return companyRepository.find(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Company get(String name) {
        return companyRepository.find(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    @Override
    @Transactional
    public void create(Company company) {
        companyRepository.create(company);
    }

    @Override
    @Transactional
    public Company update(Company company) {
        return companyRepository.update(company);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        companyRepository.delete(id);
    }

    @Override
    @Transactional
    public void delete(Company company) {
        companyRepository.delete(company);
    }
}
