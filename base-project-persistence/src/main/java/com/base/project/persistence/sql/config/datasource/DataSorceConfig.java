package com.base.project.persistence.sql.config.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DataSorceConfig {

    @Autowired
    @Qualifier("appDataSource")
    private DataSource appDataSource;

    @Bean(name="appSqlSessionFactory")
    @Primary
    //@ConditionalOnMissingBean //当容器里没有指定的Bean的情况下创建该对象
    public SqlSessionFactory appSqlSessionFactory() throws Exception {

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(appDataSource);
        bean.setTypeAliasesPackage("com.base.project.commcon.vo.result");

        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        //使用jdbc的getGeneratedKeys获取数据库自增主键值
        configuration.setUseGeneratedKeys(true);
        //使用列别名替换列名 select user as User
        configuration.setUseColumnLabel(true);
        //-自动使用驼峰命名属性映射字段   userId    user_id
        configuration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(configuration);
        bean.setFailFast(true);
        return bean.getObject();
    }
    @Bean(name="appDataSourceTransactionManager")
    @Primary
    public DataSourceTransactionManager mindDataSourceTransactionManager() {
        return new DataSourceTransactionManager(appDataSource);
    }
    @Bean(name="appSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate mindSqlSessionTemplate(@Qualifier("appSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {

        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);
        return template;
    }
}
