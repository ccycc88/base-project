package com.base.project.persistence.sql.config.intfs;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface AppMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
