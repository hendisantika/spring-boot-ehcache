package com.hendisantika.springbootehcache.repository;

import com.hendisantika.springbootehcache.model.*;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-ehcache
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 31/01/18
 * Time: 05.55
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class CompanyRepositoryImpl implements CompanyRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Cacheable(value = "company.byId", key = "#id", unless = "#result != null and #result.name.toUpperCase().startsWith('TEST')")
    public Company find(Long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Company> query = builder.createQuery(Company.class);

        Root<Company> root = query.from(Company.class);
        root.fetch(Company_.cars, JoinType.LEFT);
        Fetch<Company, Department> departmentFetch = root.fetch(Company_.departments, JoinType.LEFT);
        Fetch<Department, Employee> employeeFetch = departmentFetch.fetch(Department_.employees, JoinType.LEFT);
        employeeFetch.fetch(Employee_.address, JoinType.LEFT);
        departmentFetch.fetch(Department_.offices, JoinType.LEFT);

        query.select(root).distinct(true);
        Predicate idPredicate = builder.equal(root.get(Company_.id), id);
        query.where(builder.and(idPredicate));

        return DataAccessUtils.singleResult(entityManager.createQuery(query).getResultList());
    }

    @Override
    @Cacheable(value = "company.byName", key = "#name", unless = "#name != null and #name.toUpperCase().startsWith('TEST')")
    public Company find(String name) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Company> query = builder.createQuery(Company.class);

        Root<Company> root = query.from(Company.class);
        root.fetch(Company_.cars, JoinType.LEFT);
        Fetch<Company, Department> departmentFetch = root.fetch(Company_.departments, JoinType.LEFT);
        Fetch<Department, Employee> employeeFetch = departmentFetch.fetch(Department_.employees, JoinType.LEFT);
        employeeFetch.fetch(Employee_.address, JoinType.LEFT);
        departmentFetch.fetch(Department_.offices, JoinType.LEFT);

        query.select(root).distinct(true);
        Predicate idPredicate = builder.equal(root.get(Company_.name), name);
        query.where(builder.and(idPredicate));

        return DataAccessUtils.singleResult(entityManager.createQuery(query).getResultList());
    }

    @Override
    public List<Company> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Company> query = builder.createQuery(Company.class);
        Root<Company> root = query.from(Company.class);
        query.select(root).distinct(true);
        TypedQuery<Company> allQuery = entityManager.createQuery(query);

        return allQuery.getResultList();
    }

    @Override
    public void create(Company company) {
        entityManager.persist(company);
    }

    @Override
    @Caching(evict = {@CacheEvict(value = "company.byName", allEntries = true), @CacheEvict(value = "company.byId", key = "#result.id", condition = "#result != null and #result.name.toUpperCase().startsWith('TEST')")},
            put = {@CachePut(value = "company.byId", key = "#result.id", unless = "#result != null and #result.name.toUpperCase().startsWith('TEST')")})
    public Company update(Company company) {
        return entityManager.merge(company);
    }

    @Override
    public void delete(Long id) {
        Company company = entityManager.find(Company.class, id);
        delete(company);
    }

    @Override
    @Caching(evict = {@CacheEvict(value = "company.byId", key = "#company.id"), @CacheEvict(value = "company.byName", key = "#company.name")})
    public void delete(Company company) {
        entityManager.remove(company);
    }
}
