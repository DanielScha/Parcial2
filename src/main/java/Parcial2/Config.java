package Parcial2;

import Parcial2.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by yo on 12/6/2017.
 */
@Configuration
public class Config {

    @Autowired
    Filter filter;


    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.addUrlPatterns("/api/*");
        return registration;
    }

   @Bean
    public Connection getConnetion(@Value("${db.host}") String host,@Value("${db.port}") String port,@Value("${db.name}") String name,
                                    @Value("${db.username}") String user, @Value("${db.password}") String pass){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            return DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}