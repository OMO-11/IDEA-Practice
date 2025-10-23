package RPG_Life;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class reload_Player{

    public void reloadFile(String name) throws IOException, ClassNotFoundException {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("W:\\Machine\\IDEA\\JavaBox\\untitled\\RPG_Life\\Player_information\\" + name + ".txt"))){
            Player player;
            player = (Player) ois.readObject();
            System.out.println(player.getName());
            System.out.println(player.getAge());
        }
    }
}
