package xyz.bchen.cas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
@ComponentScan("xyz.bchen.cas")
public class SpringConfig {
    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate template = new JdbcTemplate();
        DriverManagerDataSource d = new DriverManagerDataSource();
        d.setDriverClassName("com.mysql.jdbc.Driver");
        d.setUrl("jdbc:mysql://127.0.0.1:3306/cas");
        d.setUsername("root");
        d.setPassword("123456");
        template.setDataSource(d);
        return template;
    }
}
