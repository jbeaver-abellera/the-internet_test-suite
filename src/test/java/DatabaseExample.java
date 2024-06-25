import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseExample {

    public static void main(String[] args) {
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");


        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT agent_name, working_area FROM agents")) {

            while (rs.next()) {
                String agent_name = rs.getString("AGENT_NAME");
                String working_area = rs.getString("WORKING_AREA");

                // Use retrieved data in Selenium tests
                System.out.println("Agent Name: " + agent_name + ", Working Area: " + working_area);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
