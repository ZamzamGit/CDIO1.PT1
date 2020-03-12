import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserDAO implements IUserDAO {

    private List<UserDTO> users;
    private UserStore userStore;

    public UserDAO() {
        users = new ArrayList<>();
        userStore = new UserStore();
    }

    @Override
    public UserDTO getUser(int userId) throws DALException {
        int i = 0;
        try {
            while (!(users.get(i).getUserID() == userId)) {
                i++;
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DALException("Ingen brugere med denne ID");
        }
        return users.get(i);
    }

    @Override
    public List<UserDTO> getUserList() throws DALException {
        if (users.isEmpty()) {
            throw new DALException("Ingen brugere");
        }
        return users;
    }

    @Override
    public void createUser(UserDTO user) throws DALException {
        for (UserDTO u : users) {
            if (u.getUserID() == user.getUserID()) {
                throw new DALException("Allerede en bruger med dennne ID");
            }
        }
        users.add(user);
        userStore.writeToDisk(users);
    }

    @Override
    public void updateUser(UserDTO user) throws DALException {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < users.size(); i++) {
            if ((users.get(i).getUserID() == user.getUserID())) {
                System.out.println("0 for at fortryde");
                System.out.println("1 for nyt ID");
                System.out.println("2 for nyt brugernavn");
                System.out.println("3 for nyt ini");
                System.out.println("4 for nyt CRP");
                System.out.println("5 for nyt password");
                System.out.println("6 for at tilføje en rolle");
                System.out.println("7 for at fjerne en rolle");
                int option = scanner.nextInt();
                switch (option) {
                    case 0:
                        System.out.println("Du foretager ingen ændringer\n");
                        break;
                    case 1:
                        System.out.println("Indtast nyt ID");
                        int newID = scanner.nextInt();
                        if (newID < 11 || newID > 99) {
                            throw new DALException("Ikke mellem intervallet 11 til 99");
                        } else {
                            users.get(i).setUserID(newID);
                        }
                        break;
                    case 2:
                        System.out.println("Indtast nyt brugernavn");
                        String newUsername = scanner.next();

                        if (newUsername.length() < 2 || newUsername.length() > 20) {
                            throw new DALException("Indtast nyt Brugernavn er ikke mellem 2 til 20 tegn");
                        }
                    {
                        users.get(i).setUserName(newUsername);
                    }
                    break;
                    case 3:
                        System.out.println("Indtast nyt ini");
                        String newIni = scanner.next();
                        if (newIni.length() < 2 || newIni.length() > 4) {
                            throw new DALException("Initial er ikke mellem 2 til 4 tegn");
                        } else {
                            users.get(i).setIni(newIni);
                        }
                        break;
                    case 4:
                        System.out.println("CPR");
                        String newCpr = scanner.next();
                        users.get(i).setCpr(newCpr);
                        break;
                    case 5:
                        System.out.println("Indtast nyt password");
                        String password = scanner.next();
                        users.get(i).setPassword(password);
                        break;
                    case 6:
                        System.out.println("Tilføj ny rolle: Admin, Operator, Foreman, Pharmacist");
                        String role = scanner.next();
                        if (!(role.contains("Admin") || role.contains("Operator") || role.contains("Foreman") ||
                                role.contains("Pharmacist"))) {
                            System.out.println("Ikke en gyldig rolle");

                        } else {
                            user.addRole(role);
                            System.out.println("Ny rolle tilføjet");
                        }
                        break;
                    case 7:
                        System.out.println("Indtast rolle du vil fjerne");
                        String removeRole = scanner.next();
                        user.removeRole(removeRole);
                        System.out.println("Rolle fjernet");
                        break;
            }
        }
    }
        userStore.writeToDisk(users);
}

    @Override
    public void deleteUser(int userId) throws DALException {
        boolean foundPerson = false;
        for (int i = 0; i < users.size(); i++) {
            if ((users.get(i).getUserID() == userId)) {
                System.out.println("Bruger fjernet");
                users.remove(users.get(i));
                foundPerson = true;
                userStore.writeToDisk(users);
            }
        }
        if (!foundPerson) {
            throw new DALException("Ingen bruger med denne ID");
        }
    }

    public void readFromDisk() {
        File file = new File("user.data");
        if (file.exists()) {
            users = userStore.readFromDisk();
        }
    }
}

