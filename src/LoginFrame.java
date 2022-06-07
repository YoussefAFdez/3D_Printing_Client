import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class LoginFrame extends JFrame {
    private JPanel pnlMain;
    private JPanel pnlBotones;
    private JButton btnLogin;
    private JButton btnExit;
    private JLabel lblLogin;
    private JLabel lblCredits;
    private JPanel pnlLogin;
    private JTextField txtUser;
    private JPasswordField txtPassword;
    private JLabel lblUser;
    private JLabel lblPassword;

    public LoginFrame(Connection connection) throws HeadlessException {
        super("Login");
        setContentPane(pnlMain);

        //Actions
        QuitAction quitAction = new QuitAction();
        LoginAction loginAction = new LoginAction(txtUser, txtPassword, this, connection);

        btnExit.addActionListener(quitAction);
        btnLogin.addActionListener(loginAction);
        btnLogin.setEnabled(false);

        txtUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtPassword.requestFocus();
            }
        });

        txtPassword.addActionListener(loginAction);

        Document docTxtPassword = txtPassword.getDocument();
        Document docTxtUsername = txtUser.getDocument();

        docTxtPassword.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                test();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                test();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                test();
            }

            private void test() {
                btnLogin.setEnabled(docTxtPassword.getLength() > 0 && docTxtUsername.getLength() > 0);
            }
        });

        docTxtUsername.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                test();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                test();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                test();
            }

            private void test() {
                btnLogin.setEnabled(docTxtPassword.getLength() > 0 && docTxtUsername.getLength() > 0);
            }
        });

        setBounds(150, 150, 300, 450);
        //setSize(300, 450);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }

}
