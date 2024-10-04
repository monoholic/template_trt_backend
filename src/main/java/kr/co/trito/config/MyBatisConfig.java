package kr.co.trito.config;


import kr.co.trito.utils.MybatisInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages= {"kr.co.trito.domain.repository.mybatis"}, sqlSessionFactoryRef = "sqlSessionFactory")
public class MyBatisConfig {
//
    private final DataSource dataSource;
    private final ApplicationContext applicationContext;
//
    public MyBatisConfig(DataSource dataSource, ApplicationContext applicationContext){
        this.dataSource = dataSource;
        this.applicationContext = applicationContext;
    }


    @Bean(name="sqlSessionFactory")
    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setTypeAliasesPackage("kr.co.trito.dto.Mybatis");
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(
        applicationContext.getResources("classpath:/mapper/**/*.xml"));
		sqlSessionFactoryBean.setDataSource(dataSource);
        // 로그 인터셉터
        sqlSessionFactoryBean.setPlugins(mybatisInterceptor());
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager((dataSource));
    }

    @Bean(name="sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    // 로그 인터셉터
    @Bean
    public MybatisInterceptor mybatisInterceptor(){
        return new MybatisInterceptor();
    }
}
