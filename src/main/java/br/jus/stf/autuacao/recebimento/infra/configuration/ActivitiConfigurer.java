package br.jus.stf.autuacao.recebimento.infra.configuration;

import javax.sql.DataSource;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Configuração do Activiti (BPM).
 * 
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 11.11.2016
 */
@Configuration
@Profile("default")
public class ActivitiConfigurer {

    @Value("${spring.activiti.database-schema}")
    private String schema;

    @Value("${spring.activiti.database-schema-update}")
    private String schemaUpdate;

    @Bean
    public SpringProcessEngineConfiguration processEngineConfiguration(DataSource dataSource,
            PlatformTransactionManager transactionManager) {
        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();

        configuration.setDataSource(dataSource);
        configuration.setTransactionManager(transactionManager);
        configuration.setDatabaseTablePrefix(String.format("%s.", schema));
        configuration.setTablePrefixIsSchema(true);
        configuration.setDatabaseSchemaUpdate(schemaUpdate);

        return configuration;
    }

}
