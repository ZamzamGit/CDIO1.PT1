import java.util.Date;

public class Main {

    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        UserFunk funk = new UserFunk(dao);
        TUI t = new TUI(funk);
        t.menu();
    }
}
