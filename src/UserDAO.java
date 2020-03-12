import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {

    private List<UserDTO> users;
    private UserStore userStore;

    public UserDAO() {
        users = new ArrayList<>();
        userStore = new UserStore();
    }

    @Override
    public UserDTO getUser(int userId) throws DALException {
        for (UserDTO user : users) {
            if (userId == user.getUserID()) {
                return user;
            } else {
                throw new DALException("Ingen brugere med denne ID");
            }
        }
        return null;
    }

    @Override
    public List<UserDTO> getUserList() throws DALException {
        if (users.size() == 0) {
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
        for (UserDTO u : users) {
            if(u.getUserID() != user.getUserID()) {
                throw new DALException("Ingen brugere med denne ID");
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

