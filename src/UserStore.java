import java.io.*;
import java.util.List;

public class UserStore {

    public void writeToDisk(List<UserDTO> list){
        try {
            FileOutputStream fos = new FileOutputStream("user.data");
            ObjectOutputStream outputStream = new ObjectOutputStream(fos);
            outputStream.writeObject(list);
            outputStream.close();
            fos.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public List<UserDTO> readFromDisk(){
        List<UserDTO> list = null;
        try {
            ObjectInputStream temp
                    = new ObjectInputStream(new FileInputStream("user.data"));
            list = (List<UserDTO>) temp.readObject();
            temp.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
