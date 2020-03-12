import java.util.*;

public class TUI  {

    private UserFunk funk;
    private Scanner scanner;

    public TUI(UserFunk funk) {
        this.funk = funk;
        this.scanner = new Scanner(System.in);
    }

    public void menu() {
        funk.readFromDisk();
        menuOptions();

        boolean quit = false;

        while (!quit) {
            try {
                System.out.println();
                System.out.println("Vælg en handling: ");
                int option = scanner.nextInt();
                switch (option) {
                    case 0:
                        System.out.println("Programmet er afsluttet");
                        quit = true;
                        break;
                    case 1:
                        funk.createUser();
                        break;
                    case 2:
                        funk.showUser();
                        break;
                    case 3:
                        System.out.println(funk.showAllUsers());
                        break;
                    case 4:
                        funk.updateUser();
                        break;
                    case 5:
                        funk.deleteUser();
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
}
