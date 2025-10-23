package RPG_Life;
import java.io.*;

public class save_Player {

    public void saveFile(Player p) throws IOException {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("W:\\Machine\\IDEA\\JavaBox\\untitled\\RPG_Life\\Player_information\\" + p.getName() + ".txt"))){
            out.writeObject(p);
        }
    }
}