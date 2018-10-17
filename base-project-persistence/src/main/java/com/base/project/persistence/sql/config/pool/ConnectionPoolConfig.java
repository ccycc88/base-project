package com.base.project.persistence.sql.config.pool;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@PropertySource(value = {"classpath:jdbc.properties"}, ignoreResourceNotFound=false)
public class ConnectionPoolConfig {

    @Value("${spring.datasource.url:#{null}}")
    private String dbUrl;
    @Value("${spring.datasource.username: #{null}}")
    private String username;
    @Value("${spring.datasource.password:#{null}}")
    private String password;
    @Value("${spring.datasource.driverClassName:#{null}}")
    private String driverClassName;

    @Value("${spring.datasource.initialSize:#{null}}")
    private Integer initialSize;
    @Value("${spring.datasource.minIdle:#{null}}")
    private Integer minIdle;
    @Value("${spring.datasource.maxActive:#{null}}")
    private Integer maxActive;
    @Value("${spring.datasource.maxWait:#{null}}")
    private Integer maxWait;
    @Value("${spring.datasource.timeBetweenEvictionRunsMillis:#{null}}")
    private Integer timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.minEvictableIdleTimeMillis:#{null}}")
    private Integer minEvictableIdleTimeMillis;
    @Value("${spring.datasource.validationQuery:#{null}}")
    private String validationQuery;
    @Value("${spring.datasource.testWhileIdle:#{null}}")
    private Boolean testWhileIdle;
    @Value("${spring.datasource.testOnBorrow:#{null}}")
    private Boolean testOnBorrow;
    @Value("${spring.datasource.testOnReturn:#{null}}")
    private Boolean testOnReturn;
    @Value("${spring.datasource.poolPreparedStatements:#{null}}")
    private Boolean poolPreparedStatements;
    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize:#{null}}")
    private Integer maxPoolPreparedStatementPerConnectionSize;
    @Value("${spring.datasource.filters:#{null}}")
    private String filters;
    @Value("{spring.datasource.connectionProperties:#{null}}")
    private String connectionProperties;

    @Bean(name="appDataSource", destroyMethod="close")     //声明其为Bean实例
    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
    public DataSource appDataSource() {
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setUrl(this.dbUrl);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        dataSource.setDriverClassName(this.driverClassName);
        //configuration
        if (initialSize != null) {
            dataSource.setInitialSize(initialSize);
        }
        if (minIdle != null) {
            dataSource.setMinIdle(minIdle);
        }
        if (maxActive != null) {
            dataSource.setMaxActive(maxActive);
        }
        if (maxWait != null) {
            dataSource.setMaxWait(maxWait);
        }
        if (timeBetweenEvictionRunsMillis != null) {
            dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        }
        if (minEvictableIdleTimeMillis != null) {
            dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        }
        if (validationQuery != null) {
            dataSource.setValidationQuery(validationQuery);
        }
        if (testWhileIdle != null) {
            dataSource.setTestWhileIdle(testWhileIdle);
        }
        if (testOnBorrow != null) {
            dataSource.setTestOnBorrow(testOnBorrow);
        }
        if (testOnReturn != null) {
            dataSource.setTestOnReturn(testOnReturn);
        }
        if (poolPreparedStatements != null) {
            dataSource.setPoolPreparedStatements(poolPreparedStatements);
        }
        if (maxPoolPreparedStatementPerConnectionSize != null) {
            dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        }

        if (connectionProperties != null) {
            dataSource.setConnectionProperties(connectionProperties);
        }

        List<Filter> filters = new ArrayList<>();
        filters.add(statFilter());
        filters.add(wallFilter());
        dataSource.setProxyFilters(filters);

        return dataSource;
    }

    @Bean
    @Primary
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        //添加密码访问
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("loginUsername","admin");
        initParameters.put("loginPassword","admin");
        initParameters.put("resetEnable", "false");// 禁用HTML页面上的“Reset All”功能
        initParameters.put("allow", "127.0.0.1"); // IP白名单 (没有配置或者为空，则允许所有访问)
        //initParameters.put("deny", "192.168.20.38");// IP黑名单 (存在共同时，deny优先于allow)
        servletRegistrationBean.setInitParameters(initParameters);
        return servletRegistrationBean;
    }

    @Bean
    @Primary
    public StatFilter statFilter() {
        StatFilter statFilter = new StatFilter();
        statFilter.setLogSlowSql(true);
        statFilter.setMergeSql(true);
        statFilter.setSlowSqlMillis(1000);

        return statFilter;
    }

    @Bean
    @Primary
    public WallFilter wallFilter() {
        WallFilter wallFilter = new WallFilter();

        //允许执行多条SQL
        WallConfig config = new WallConfig();
        config.setMultiStatementAllow(true);
        wallFilter.setConfig(config);

        return wallFilter;
    }
}
