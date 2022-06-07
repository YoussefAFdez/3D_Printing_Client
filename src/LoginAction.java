import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class LoginAction extends AbstractAction {
    private JTextField user;
    private JPasswordField password;
    private Connection connection;
    private JFrame ventanaLogin;

    public LoginAction(JTextField user, JPasswordField password, LoginFrame ventanaLogin, Connection connection) {
        this.user = user;
        this.password = password;
        this.connection = connection;
        this.ventanaLogin = ventanaLogin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!comprobar()) {
            JOptionPane.showMessageDialog(ventanaLogin, "Wrong username and password");
            user.setText("");
            password.setText("");
            user.requestFocus();
        } else {
            ventanaLogin.setVisible(false);
            user.setText("");
            password.setText("");
            user.requestFocus();
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement("SELECT userid FROM users");
                ResultSet result = statement.executeQuery();
                if (result.next()) {
                    new MainFrame(ventanaLogin, connection, result.getInt("userid"));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private boolean comprobar() {
        boolean returnValue = false;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT count(*) AS counter FROM users WHERE username = ? AND password = ?");
            statement.setString(1, user.getText());
            statement.setString(2, String.valueOf(password.getPassword()));
            ResultSet result = statement.executeQuery();
            if (result.next()) returnValue = result.getInt("counter") == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return returnValue;
    }
}
