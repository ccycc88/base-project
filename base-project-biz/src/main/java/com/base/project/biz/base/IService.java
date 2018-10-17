package com.base.project.biz.base;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IService<T> {

    int delete(T t);

    int insert(T t);

    int insertSelective(T t);

    T selectOne(T t);

    List<T> select(T t);

    int updateByPrimaryKeySelective(T t);

    List<T> selectAll();
}
