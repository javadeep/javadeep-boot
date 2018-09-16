package com.javadeep.boot.jpa.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.Nullable;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * Javadeep Repository
 *
 * @author javadeep
 * @since 1.0.0
 */
@NoRepositoryBean
public interface JavadeepRepository<T, ID extends Serializable> extends JpaRepository<T, ID>,
        JpaSpecificationExecutor<T> {

    /**
     * 获取EntityManager
     *
     * @return 返回entityManager
     */
    EntityManager getEm();

    /**
     * 获取domainClass
     *
     * @return 返回domainClass
     */
    Class<T> getDomainClass();

    /**
     * 插入一个实体
     *
     * @param entity 实体
     * @param <S> 实体的类型
     * @return 返回实体
     */
    <S extends T> S insert(S entity);

    /**
     * 插入一个实体，并flush
     *
     * @param entity 实体
     * @param <S> 实体的类型
     * @return 返回实体
     */
    <S extends T> S insertAndFlush(S entity);

    /**
     * 插入实体列表
     *
     * @param entities 实体列表
     * @param <S> 实体的类型
     * @return 返回实体列表
     */
    <S extends T> List<S> insertAll(Iterable<S> entities);

    /**
     * 根据spec查询BO
     *
     * @param BOClass BO的类型
     * @param spec spec
     * @param <BO> BO对象
     * @return 返回查询结果BO
     */
    <BO> List<BO> findAllBO(Class<BO> BOClass, @Nullable Specification<T> spec);

    /**
     *
     * 根据spec和sort查询BO
     *
     * @param BOClass BO的类型
     * @param spec spec
     * @param sort 排序方式
     * @param <BO> BO对象
     * @return 返回查询结果BO
     */
    <BO> List<BO> findAllBO(Class<BO> BOClass, @Nullable Specification<T> spec, Sort sort);

}
