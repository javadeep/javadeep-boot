package com.javadeep.boot.jpa.repository.support;

import com.javadeep.boot.jpa.repository.JavadeepRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.data.jpa.repository.query.QueryUtils.toOrders;

/**
 * Javadeep Repository的默认实现
 *
 * @author javadeep
 * @since 1.0.0
 */
@Repository
@Transactional(readOnly = true)
public class SimpleJavadeepRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
        implements JavadeepRepository<T, ID> {

    private final EntityManager em;
    private final Class<T> domainClass;

    public SimpleJavadeepRepository(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.em = em;
        this.domainClass = domainClass;
    }

    @Override
    public EntityManager getEm() {
        return em;
    }

    @Override
    @Transactional
    public <S extends T> S insert(S entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    @Transactional
    public <S extends T> S insertAndFlush(S entity) {
        S result = insert(entity);
        flush();
        return result;
    }

    @Override
    @Transactional
    public <S extends T> List<S> insertAll(Iterable<S> entities) {
        Assert.notNull(entities, "The given Iterable of entities not be null!");
        return StreamSupport.stream(entities.spliterator(), false)
                .map(this::insert)
                .collect(Collectors.toList());
    }

    @Override
    public Class<T> getDomainClass() {
        return domainClass;
    }

    @Override
    public <BO> List<BO> findAllBO(Class<BO> BOClass, @Nullable Specification<T> spec) {
        return findAllBO(BOClass, spec, Sort.unsorted());
    }

    @Override
    public <BO> List<BO> findAllBO(Class<BO> BOClass, @Nullable Specification<T> spec, Sort sort) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<BO> query = builder.createQuery(BOClass);
        Root<T> root = query.from(domainClass);
        if (spec != null) {
            Predicate predicate = spec.toPredicate(root, query, builder);
            if (predicate != null) {
                query.where(predicate);
            }
        }

        if (sort.isSorted()) {
            query.orderBy(toOrders(sort, root, builder));
        }

        return em.createQuery(query).getResultList();
    }
}
