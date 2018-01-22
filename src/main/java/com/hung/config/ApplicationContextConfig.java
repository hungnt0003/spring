package com.hung.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * Khai báo spring bean.
 *
 *
 * @author Mitsui Zosen Systems Research Inc.
 * @version X.X
 * @since TIME-3 X.X
 */
@Configuration
// Tìm kiếm các bean khác được khai báo @Controller, @Service, @component... trong package con của com.hung
@ComponentScan("com.hung.*")
@EnableTransactionManagement
@PropertySource("classpath:setting/web/connection-config.properties")
public class ApplicationContextConfig {

    /** Environment. */
    @Resource
    private Environment env;

    // Tạo bean name viewResolver
    // @Bean(name = "viewResolver")
    // public InternalResourceViewResolver getViewResolver() {
    // InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    // // Các file view sẽ được lưu vào /WEB-INF/pages/, có phần mở rộng là *.jsp
    // viewResolver.setPrefix("/WEB-INF/views/");
    // viewResolver.setSuffix(".jsp");
    // return viewResolver;
    // }

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        // See: datasouce-cfg.properties
        dataSource.setDriverClassName(env.getProperty("web.datasource.driverClassName"));
        dataSource.setUrl(env.getProperty("web.datasource.URL"));
        dataSource.setUsername(env.getProperty("web.datasource.username"));
        dataSource.setPassword(env.getProperty("web.datasource.password"));

        System.out.println("## getDataSource: " + dataSource);

        return dataSource;
    }

    // Transaction Manager
    @Autowired
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager getTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);

        return transactionManager;
    }
}
