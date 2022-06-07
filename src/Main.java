import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:32768/printing_db", "postgres", "oretania");
            System.out.println("Conexion abierta con la base de datos");
            LoginFrame loginFrame = new LoginFrame(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
