package me.warriorg.dubbo.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import me.warriorg.dubbo.springboot.prop.DruidDataSourceProperty;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@Slf4j
public class MybatisConfig {

    @Autowired
    DruidDataSourceProperty druidDataSourceProperty;

    @Bean
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(druidDataSourceProperty.getUrl());
        datasource.setUsername(druidDataSourceProperty.getUsername());
        datasource.setPassword(druidDataSourceProperty.getPassword());
        datasource.setDriverClassName(druidDataSourceProperty.getDriverClassName());
        datasource.setInitialSize(druidDataSourceProperty.getInitialSize());
        datasource.setMinIdle(druidDataSourceProperty.getMinIdle());
        datasource.setMaxActive(druidDataSourceProperty.getMaxActive());
        // 配置获取连接等待超时的时间
        datasource.setMaxWait(druidDataSourceProperty.getMaxWait());
        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        datasource.setTimeBetweenEvictionRunsMillis(druidDataSourceProperty.getTimeBetweenEvictionRunsMillis());
        // 配置一个连接在池中最小生存的时间，单位是毫秒
        datasource.setMinEvictableIdleTimeMillis(druidDataSourceProperty.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(druidDataSourceProperty.getValidationQuery());
        datasource.setTestWhileIdle(druidDataSourceProperty.isTestWhileIdle());
        datasource.setTestOnBorrow(druidDataSourceProperty.isTestOnBorrow());
        datasource.setTestOnReturn(druidDataSourceProperty.isTestOnReturn());
        datasource.setPoolPreparedStatements(druidDataSourceProperty.isPoolPreparedStatements());
        datasource.setMaxPoolPreparedStatementPerConnectionSize(druidDataSourceProperty.getMaxPoolPreparedStatementPerConnectionSize());
        datasource.setUseGlobalDataSourceStat(druidDataSourceProperty.isUseGlobalDataSourceStat());
        try {
            datasource.setFilters(druidDataSourceProperty.getFilters());
        } catch (SQLException e) {
            log.error("druid configuration initialization filter", e);
        }
        datasource.setConnectionProperties(druidDataSourceProperty.getConnectionProperties());

        return datasource;

    }



}
