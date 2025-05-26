package admintechinventory.Controllers.Login;

import admintechinventory.Dao.Login.UserDao;
import admintechinventory.Dao.Login.RolDao;
import admintechinventory.Views.Login.JfrmLoginUser;
import admintechinventory.Models.User;
import admintechinventory.Models.Sesion;
import admintechinventory.Views.Home.JfrmHome;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class LoginController {

    private JfrmLoginUser view;
    private Connection connection;
    private UserDao userdao;
    private RolDao roldao;

    public LoginController(JfrmLoginUser view, Connection connection) {
        this.view = view;
        this.connection = connection;
        this.userdao = new UserDao(connection);
        this.roldao = new RolDao(connection);
        initEvents();
    }

    private void initEvents() {
        view.getBtnSignIn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
    }

    private void login() {

        String username = view.getTxtUsername().getText().trim();
        String password = String.valueOf(view.getTxtPassword().getPassword()).trim();

        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Por favor ingrese su usuario",
                    "Campo requerido", JOptionPane.WARNING_MESSAGE);
            view.getTxtUsername().requestFocus();
            return;
        }

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Por favor ingrese su contraseña",
                    "Campo requerido", JOptionPane.WARNING_MESSAGE);
            view.getTxtPassword().requestFocus();
            return;
        }

        Sesion.closedSesion();

        try {
            User user = userdao.validateUser(username, password);

            if (user != null) {
                Sesion.userActuality = user;

                
                JOptionPane.showMessageDialog(view,
                        "¡Bienvenido " + user.getUsername() + "!\nRol: " + user.getRole(),
                        "Login exitoso", JOptionPane.INFORMATION_MESSAGE);
                view.getTxtUsername().setText("");
                view.getTxtPassword().setText("");
                view.dispose();
                openHome(user);
            } else {
                JOptionPane.showMessageDialog(view,
                        "Usuario o contraseña incorrectos.\nVerifique sus credenciales.",
                        "Error de autenticación", JOptionPane.ERROR_MESSAGE);
                view.getTxtPassword().setText("");
                view.getTxtUsername().requestFocus();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(view,
                    "Error inesperado: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openHome(User user) {
        try {
            JfrmHome home = new JfrmHome();
            if ("Staff".equalsIgnoreCase(user.getRole())) {
                home.adminControllers();
            }
            home.setLocationRelativeTo(null);
            home.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error al abrir la ventana principal: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            reopenLogin();
        }
    }

    private void reopenLogin() {
        try {
            JfrmLoginUser newLogin = new JfrmLoginUser();
            LoginController controller = new LoginController(newLogin, connection);
            newLogin.setLocationRelativeTo(null);
            newLogin.setVisible(true);
        } catch (Exception e) {
            System.exit(1);
        }
    }
}
