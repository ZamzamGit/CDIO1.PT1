import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class UserFunk implements IUserFunk {

    private UserDAO dao;
    private Scanner scanner;
    private UserStore userStore;

    public UserFunk(UserDAO dao) {
        this.dao = dao;
        this.scanner = new Scanner(System.in);
        this.userStore = new UserStore();
    }

    @Override
    public void createUser() throws DALException {
        System.out.println("ID");
        int userID = scanner.nextInt();
        if (userID < 11 || userID > 99) {
            throw new DALException("Ikke mellem intervallet 11 til 99");
        }
        System.out.println("Brugernavn");
        String userName = scanner.next();
        if (userName.length() < 2 || userName.length() > 20) {
            throw new DALException("Brugernavn er ikke mellem 2 til 20 tegn");
        }

        System.out.println("Ini");
        String ini = scanner.next();
        if (ini.length() < 2 || ini.length() > 4) {
            throw new DALException("Initial er ikke mellem 2 til 4 tegn");
        }
        System.out.println("CPR");
        String cpr = scanner.next();
        String password = password();
        UserDTO user;
        user = new UserDTO(userID, userName, ini, cpr, password);
        System.out.println("Tilføj gyldig rolle: Admin, Operator, Foreman, Pharmacist");
        String role = scanner.next();
        if (!(role.contains("Admin") || role.contains("Operator") || role.contains("Foreman") ||
                role.contains("Pharmacist"))) {
            System.out.println("Ikke gyldige rolle");
        } else {
            user.addRole(role);
            dao.createUser(user);
            System.out.println("Bruger oprettet\n");
        }
    }

    @Override
    public void updateUser() throws DALException {
        System.out.println("Indtast ID på brugeren du vil ændre");
        int id = scanner.nextInt();
        dao.updateUser(dao.getUser(id));
    }

    @Override
    public void deleteUser() throws DALException {
        System.out.println("Indtast ID på brugeren du vil slette");
        int id = scanner.nextInt();
        dao.deleteUser(id);
    }

    @Override
    public void showUser() throws DALException {
        System.out.println("Indtast id");
        int id = scanner.nextInt();
        System.out.println(dao.getUser(id).toString());

    }

    @Override
    public List<UserDTO> showAllUsers() throws DALException {
        return dao.getUserList();
    }

    @Override
    public String password() {
        String character = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 8;
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        char[] text = new char[length];

        for (int i = 0; i < length; i++) {
            text[i] = character.charAt(random.nextInt(character.length()));
        }
        for (int i = 0; i < text.length; i++) {
            password.append(text[i]);
        }
        return password.toString();
    }

    public void readFromDisk() {
        dao.readFromDisk();
    }
}
