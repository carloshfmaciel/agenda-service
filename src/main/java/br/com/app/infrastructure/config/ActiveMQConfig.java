package br.com.app.infrastructure.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@EnableJms
@Configuration
public class ActiveMQConfig {

	@Value("${spring.activemq.broker-url}")
	private String brokerUrl;

	@Value("${spring.activemq.broker-username}")
	private String brokerUsername;

	@Value("${spring.activemq.broker-password}")
	private String brokerPassword;

	@Bean
	ActiveMQConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(brokerUrl);
		connectionFactory.setPassword(brokerUsername);
		connectionFactory.setUserName(brokerPassword);
		return connectionFactory;
	}

	@Bean
	CachingConnectionFactory cachingConnectionFactory() {
		return new CachingConnectionFactory(connectionFactory());
	}

	// O pool de conexoes pode ser utilizado, pois criar conexoes e algo custoso
	@Bean
	PooledConnectionFactory pooledConnectionFactory() {
		PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory(connectionFactory());
		pooledConnectionFactory.setMaxConnections(10);
		return pooledConnectionFactory;
	}

	@Bean
	JmsTemplate jmsTemplate() {
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(cachingConnectionFactory());
		template.setPubSubDomain(true);
		return template;
	}

	@Bean
	DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(cachingConnectionFactory());
		factory.setPubSubDomain(true);
		return factory;
	}

}
