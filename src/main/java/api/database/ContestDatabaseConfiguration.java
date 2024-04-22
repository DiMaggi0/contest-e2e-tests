package api.database;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("api.database")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "api.database.repos")
public class ContestDatabaseConfiguration {
    @Bean
    @ConfigurationProperties("datasource.contest")
    public DataSourceProperties contestDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public JdbcTemplate contestJdbcTemplate() { return new JdbcTemplate(contestDataSource());}

    @Bean
    @ConfigurationProperties("datasource.contest.configuration")
    public HikariDataSource contestDataSource() {
        return contestDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }



}
