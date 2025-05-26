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
        String username = view.getTxtUsername().getText();
        String password = String.valueOf(view.getTxtPassword().getPassword());

        User user = new User(username, password);

        boolean isValid = userdao.validateUser(user);

        if (isValid) {
            String role = Sesion.userActuality.getRole();
            JOptionPane.showMessageDialog(view, "Welcome, " + role + "!");

            view.dispose();
            JfrmHome pageJfrmHome = new JfrmHome();

            // Si es trabajador, aplicamos restricciones
            if (role.equalsIgnoreCase("Staff")) {
                pageJfrmHome.adminControllers();
            }

            pageJfrmHome.setLocationRelativeTo(null);
            pageJfrmHome.setVisible(true);

            System.out.println("Usuario conectado");

        } else {
            JOptionPane.showMessageDialog(view, "Incorrect login details.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
