package GameParty.User.File;

import GameParty.User.UserProfile;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class reload {
    private static final String INFORMATION_PATH = "W:\\Machine\\IDEA\\JavaBox\\untitled\\GameParty\\User\\User_Information\\";

    public static UserProfile reloadFile(String userName) throws IOException, ClassNotFoundException{
        UserProfile user;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(INFORMATION_PATH + userName + ".txt"))){
            user = (UserProfile)ois.readObject();
        }
        return user;
    }
}
