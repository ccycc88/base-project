package com.base.project.biz.base;

import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BaseService<T> implements IService<T>{

    @Autowired
    private Mapper<T> mapper;

    public Mapper<T> getMapper() {
        return mapper;
    }

    public void setMapper(Mapper<T> mapper) {
        this.mapper = mapper;
    }

    @Override
    public int delete(T t) {
        // TODO Auto-generated method stub
        return mapper.delete(t);
    }

    @Override
    public int insert(T t) {
        // TODO Auto-generated method stub
        return mapper.insert(t);
    }

    @Override
    public int insertSelective(T t) {
        // TODO Auto-generated method stub
        return mapper.insertSelective(t);
    }

    @Override
    public T selectOne(T t) {

        return mapper.selectOne(t);
    }

    @Override
    public List<T> select(T t) {
        // TODO Auto-generated method stub
        return mapper.select(t);
    }

    @Override
    public int updateByPrimaryKeySelective(T t) {
        // TODO Auto-generated method stub
        return mapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public List<T> selectAll() {
        // TODO Auto-generated method stub
        return mapper.selectAll();
    }
}
