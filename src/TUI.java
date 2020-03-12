import java.util.InputMismatchException;
import java.util.Scanner;

public class TUI implements ITUI{

    private UserDAO dao;
    private Scanner scanner;

    public TUI(UserDAO dao) {
        this.dao = dao;
        this.scanner = new Scanner(System.in);

    }

    public void menu() {
        dao.readFromDisk();
        menuOptions();

        boolean quit = false;

        while (!quit) {
            try {
                System.out.println("Vælg en handling: ");
                int option = scanner.nextInt();
                switch (option) {
                    case 0:
                        System.out.println("Programmet er afsluttet");
                        quit = true;
                        break;
                    case 1:
                        createUser();
                        break;
                    case 2:
                        System.out.println("Indtast id");
                        int id = scanner.nextInt();
                        System.out.println(dao.getUser(id).toString());
                        break;
                    case 3:
                        System.out.println(dao.getUserList());
                        System.out.println();
                        break;
                    case 4:


                        break;
                    case 5:
                        System.out.println("Indtast ID på brugeren du vil slette");
                        id = scanner.nextInt();
                        dao.deleteUser(id);
                        break;
                    case 6:
                        menuOptions();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Fejl i input, prøv igen\n");
                scanner.nextLine();
            } catch (DALException e) {
                System.out.println(e.getMessage() + "\n");
                scanner.nextLine();
            }
        }
    }

    public void menuOptions() {
        System.out.println("0 for afslutte programmet");
        System.out.println("1 for at oprette ny bruger");
        System.out.println("2 for at finde oplysninger for en bruger");
        System.out.println("3 for at se en liste af brugere");
        System.out.println("4 for at rette en bruger");
        System.out.println("5 for at slette en bruger");
        System.out.println("6 for at se valgmulighederne\n");
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
        if (ini.length() < 2 || userName.length() > 4) {
            throw new DALException("Initial er ikke mellem 2 til 4 tegn");
        }
            System.out.println("CPR");
            String cpr = scanner.next();
            System.out.println("Password");
            String password = scanner.next();
            UserDTO user;
            // String roles = scanner.nextLine();
            //user.addRole(roles);
            user = new UserDTO(userID, userName, ini, cpr, password);
            dao.createUser(user);
            System.out.println("Bruger oprettet\n");
        }

    @Override
    public void updateUser() {

    }

    @Override
    public void deleteUser() {

    }

    @Override
    public void showUser() {

    }

    @Override
    public void showAllUsers() {

    }
}
