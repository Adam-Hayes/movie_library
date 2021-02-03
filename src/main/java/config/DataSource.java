package config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private static DataSource instance;

    private static HikariConfig config;
    private static HikariDataSource ds;

    private DataSource() {
        config = new HikariConfig("/hikaricp.properties");
        ds = new HikariDataSource(config);
    }

    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }

        return instance;
    }

    public Connection getConnection() throws SQLException {
        init();
        return ds.getConnection();
    }

    private void init() {
        Flyway flyway = Flyway.configure().dataSource(ds).load();
        flyway.migrate();
    }
}
