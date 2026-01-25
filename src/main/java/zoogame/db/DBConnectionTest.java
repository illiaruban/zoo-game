package zoogame.db;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionTest {

    public static void main(String[] args) {
        try {
            System.out.println("cwd=" + System.getProperty("user.dir"));

            Dotenv env = Dotenv.configure()
                    .directory(System.getProperty("user.dir"))
                    .load();

            String url = env.get("DB_URL");
            String user = env.get("DB_USER");
            String password = env.get("DB_PASSWORD");

            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                System.out.println("Connected to DB successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}
