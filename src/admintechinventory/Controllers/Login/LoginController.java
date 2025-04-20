package admintechinventory.Controllers.Login;

import admintechinventory.Dao.Login.UserDao;
import admintechinventory.Dao.Login.RolDao;
import admintechinventory.Views.Login.JfrmLoginUser;
import admintechinventory.Models.User;
import admintechinventory.Models.Rol;
import admintechinventory.Views.Home.JfrmHome;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import sun.java2d.Disposer;

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
        loadRoles();
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

    private void loadRoles() {
        ArrayList<Rol> roles = roldao.getAllRoles();
        view.getCmbRole().removeAllItems();
        for (Rol role : roles) {
            view.getCmbRole().addItem(role.getName());
        }
    }

    private void login() {
        String role = view.getCmbRole().getSelectedItem().toString();
        String username = view.getTxtUsername().getText();
        String password = String.valueOf(view.getTxtPassword().getPassword());

        User user = new User(username, password, role);

        boolean isValid = userdao.validateUser(user);

        if (isValid) {
            JOptionPane.showMessageDialog(view, "Welcome, " + role + "!");
            if (role.equals("Admin")) {
                view.dispose(); 
                JfrmHome pageJfrmHome = new JfrmHome();
                pageJfrmHome.setLocationRelativeTo(null);
                pageJfrmHome.setVisible(true);
            }
            System.out.println("Usuario conectado");

        } else {
            JOptionPane.showMessageDialog(view, "Incorrect login details.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
