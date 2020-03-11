import java.util.Date;

public class Main {

    public static void main(String[] args) {

        UserDAO dao = new UserDAO();
        TUI t = new TUI(dao);
        t.menu();
    }
}
