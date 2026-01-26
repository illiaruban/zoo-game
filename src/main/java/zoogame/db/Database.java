package zoogame.db;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Database instance;
    private Connection connection;

    private Database() throws SQLException {
        Dotenv env = Dotenv.configure()
                .directory(System.getProperty("user.dir"))
                .load();

        String url = env.get("DB_URL");
        String user = env.get("DB_USER");
        String password = env.get("DB_PASSWORD");

        connection = DriverManager.getConnection(url, user, password);
    }

    public static Database getInstance() throws SQLException {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed() || !connection.isValid(2)) {
            throw new SQLException("DB connection is not available");
        }
        return connection;
    }
}
