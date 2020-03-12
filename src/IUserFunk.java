import java.util.List;

public interface IUserFunk {

    void createUser() throws DALException;
    void updateUser() throws DALException;
    void deleteUser() throws DALException;
    void showUser() throws DALException;
    List<UserDTO> showAllUsers() throws DALException;
    String password();
}
