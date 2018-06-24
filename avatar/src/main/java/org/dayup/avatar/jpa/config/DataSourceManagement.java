
package org.dayup.avatar.jpa.config;

import org.dayup.avatar.base.plugins.fool.FoolRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(repositoryBaseClass = FoolRepositoryImpl.class, basePackages = "org.dayup.avatar.jpa.repository")
public class DataSourceManagement {


    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    private DataSource dataSource;

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryOtms(EntityManagerFactoryBuilder builder) {

        return builder.dataSource(dataSource).properties(jpaProperties.getHibernateProperties(new HibernateSettings()))
                .packages("org.dayup.avatar.jpa.entity").build();
    }


}
