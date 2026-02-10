package com.example.spring1.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class JdbcConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.iii")
    public DataSource iiiDataSource() {
        System.out.println("iii.OK");
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.north")
    public DataSource northDataSource() {
        System.out.println("north.OK");
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public NamedParameterJdbcTemplate iiiJdbc(
            @Qualifier("iiiDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate northJdbc(
            @Qualifier("northDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
