package com.base.project.persistence.sql.config.scanner;

import com.base.project.persistence.sql.config.datasource.DataSorceConfig;
import com.base.project.persistence.sql.config.intfs.AppMapper;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

@Configuration
@AutoConfigureAfter(DataSorceConfig.class)
public class MapperScannerConfig {
    @Bean
    public MapperScannerConfigurer appMapperScannerConfigurer() {

        Properties properties=new Properties();
        properties.put("mappers","tk.mybatis.mapper.common.Mapper,tk.mybatis.mapper.common.MySqlMapper");

        MapperScannerConfigurer configurer = new MapperScannerConfigurer();

        configurer.getMapperHelper().setProperties(properties);
        configurer.setSqlSessionTemplateBeanName("mindSqlSessionTemplate");
        configurer.setBasePackage("com.qihoo.os.mind.persistence.sql.mapper");
        configurer.setMarkerInterface(AppMapper.class);
        return configurer;
    }
}
