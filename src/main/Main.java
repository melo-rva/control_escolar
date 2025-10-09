
package main;

import controller.LoginController;
import model.AppModel;
import model.AuthService;
import view.AlumnoView;
import view.LoginDialog;

public class Main {
    public static void main(String[] args) {
        AppModel model = new AppModel();
        AuthService auth = new AuthService();
        LoginDialog dialog = new LoginDialog(null);
        AlumnoView alumnoView = new AlumnoView();

        LoginController controller = new LoginController(model, auth, dialog, alumnoView);
        dialog.setVisible(true);
    }
}