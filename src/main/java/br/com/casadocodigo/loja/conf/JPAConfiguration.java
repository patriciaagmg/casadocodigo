package br.com.casadocodigo.loja.conf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@EnableTransactionManagement //Para o spring tomar conta da transação.
public class JPAConfiguration {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
	
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	
		factoryBean.setJpaVendorAdapter(vendorAdapter);
		
		Properties properties = new Properties();

		//InputStream is = getClass().getResourceAsStream("../../../../../config.properties");
		InputStream is = getClass().getResourceAsStream("../../../../../config.properties");
		
		try {
			properties.load(is);
			
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setUsername(properties.getProperty("dataSource.username"));
			dataSource.setPassword(properties.getProperty("dataSource.password"));
			dataSource.setUrl(properties.getProperty("dataSource.url"));
			dataSource.setDriverClassName(properties.getProperty("dataSource.driverClassName"));

			factoryBean.setDataSource(dataSource);		
		
			factoryBean.setJpaProperties(properties);
			
			factoryBean.setPackagesToScan("br.com.casadocodigo.loja.models"); /*Scanear as entidades*/
			
		} catch (IOException e) {
			//Tratar exception
			e.printStackTrace();
		}
						
	
		
		return  factoryBean;
	}
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf){
		return new JpaTransactionManager(emf);
	}
	
	/**
	 * Conversor para arquivos.
	 * @return
	 */
	@Bean
	public MultipartResolver multipartResolver(){
		return new StandardServletMultipartResolver();
	}
	
	
}
