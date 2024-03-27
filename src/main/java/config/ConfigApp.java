package config;
import javax.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"controller", "service", "dao"})
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true) //!!! add when beens realise interfaces
@EnableWebMvc //добавить только после того как будет подключен web
public class ConfigApp {

    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory factory,
                                                         DataSource source) {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(factory);
        manager.setDataSource(source);
        return manager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource source,
                                                                       JpaVendorAdapter adapter) {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();

        emfb.setDataSource(source);
        emfb.setJpaVendorAdapter(adapter);
        emfb.setPackagesToScan("domain");
        return emfb;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/bulletin_board?serverTimezone=Europe/Warsaw");
        dataSource.setUsername("root");
        dataSource.setPassword("12345678");
        return dataSource;
    }

    @Bean
    public JpaVendorAdapter adapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false); //создавать таблицы или нет
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");

        return adapter;
    }

}
