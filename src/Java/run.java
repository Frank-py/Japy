package Java;

public class run {
  public static testuser user;

  public static void main(String[] args) {
    // new messtest();
    login.createWindow();
  }

  public static void loggedin(String[] UP) {
    user = new testuser(UP);
  }

  public static testuser getUser() {
    return user;
  }
}