package org.yang.springboot.shiro.service.base;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.Assert;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * org.yang.springboot.shiro.service.base.BaseService
 *
 * @author eleven
 * @date 2019/05/27
 */
public abstract class BaseService {

    /**
     * 获取Jpa实例
     *
     * @return
     */
    protected abstract JpaRepository getJpaRepository();

    /**
     * 根据条件分页获取数据
     *
     * @param t
     * @param exampleMatcher
     * @param page
     * @param size
     * @param <T>
     * @return
     */
    protected <T> Page<T> findListWithPage(T t, ExampleMatcher exampleMatcher, int page, int size) {
        Example<T> example = new Example<T>() {
            @Override
            public T getProbe() {
                return t;
            }

            @Override
            public ExampleMatcher getMatcher() {
                return exampleMatcher;
            }
        };

        return getJpaRepository().findAll(example, PageRequest.of(page, size));
    }

    /**
     * 根据条件获取唯一数据
     *
     * @param t
     * @param exampleMatcher
     * @param message
     * @param <T>
     * @return
     */
    protected <T> T findOne(T t, ExampleMatcher exampleMatcher, String message) {
        Example<T> example = new Example<T>() {
            @Override
            public T getProbe() {
                return t;
            }

            @Override
            public ExampleMatcher getMatcher() {
                return exampleMatcher;
            }
        };

        Optional<T> result = getJpaRepository().findOne(example);

        result.orElseThrow(() -> new EntityNotFoundException(message));

        return result.get();
    }

    /**
     * 根据条件获取所有数据
     *
     * @param t
     * @param exampleMatcher
     * @param message
     * @param <T>
     * @return
     */
    protected <T> List<T> findAll(T t, ExampleMatcher exampleMatcher, String message) {
        Example<T> example = new Example<T>() {
            @Override
            public T getProbe() {
                return t;
            }

            @Override
            public ExampleMatcher getMatcher() {
                return exampleMatcher;
            }
        };

        List<T> all = getJpaRepository().findAll(example);

        Assert.notEmpty(all, message);

        return all;
    }

    /**
     * 通过ID获取数据
     *
     * @param id
     * @param message
     * @param <T>
     * @return
     */
    protected <T> T findById(Integer id, String message) {
        Optional<T> optional = getJpaRepository().findById(id);

        optional.orElseThrow(() -> new EntityNotFoundException(message));

        return optional.get();
    }

    /**
     * 获取所有数据
     *
     * @param message
     * @param <T>
     * @return
     */
    protected <T> List<T> findAll(String message) {
        List<T> all = getJpaRepository().findAll();

        Assert.notEmpty(all, message);

        return all;
    }

    /**
     * 获取所有数据
     *
     * @param message
     * @param <T>
     * @return
     */
    protected <T> List<T> findAll(String message, Sort sort) {
        List<T> all = getJpaRepository().findAll(sort);

        Assert.notEmpty(all, message);

        return all;
    }
}
