package GameParty.User.File;

import GameParty.User.UserProfile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class upload{
    private static final String INFORMATION_PATH = "W:\\Machine\\IDEA\\JavaBox\\untitled\\GameParty\\User\\User_Information\\";

    public static void uploadFile(UserProfile user) throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(INFORMATION_PATH + user.getUsername() + ".txt"))) {
            oos.writeObject(user);
        }
    }
}
