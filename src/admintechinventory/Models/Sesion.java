package admintechinventory.Models;

public class Sesion {
    
    public static User userActuality;
    
    public static void closedSesion() {
        if (userActuality != null) {
            System.out.println("Cerrando sesión de: " + userActuality.getUsername());
        }
        userActuality = null;
        System.out.println("Sesión cerrada correctamente");
    }
    
    public static boolean isLoggedIn() {
        return userActuality != null;
    }
    
    public static String getCurrentUsername() {
        return userActuality != null ? userActuality.getUsername() : null;
    }
    
    public static String getCurrentRole() {
        return userActuality != null ? userActuality.getRole() : null;
    }
}