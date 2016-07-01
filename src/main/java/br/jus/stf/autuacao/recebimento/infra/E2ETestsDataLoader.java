package br.jus.stf.autuacao.recebimento.infra;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
@Profile("e2e")
public class E2ETestsDataLoader {

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	public void init() throws SQLException {
		System.out.println(runtimeService);
		loadDataTests("assinarOficioDevolucaoRemessaOriginario.sql", "devolverRemessaOriginario.sql",
				"preautarRemessaOriginario.sql", "preautuarRemessaRecursal.sql",
				"prepararOficioDevolucaoRemessaOriginario.sql");
	}

	protected void loadDataTests(String... scriptsSql) throws SQLException {
		Connection connection = null;
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();

		for (String scriptSql : scriptsSql) {
			populator.addScript(new ClassPathResource("/db/tests/" + scriptSql));
		}

		try {
			connection = DataSourceUtils.getConnection(dataSource);
			populator.populate(connection);
		} finally {
			if (connection != null) {
				DataSourceUtils.releaseConnection(connection, dataSource);
			}
		}
	}

}
