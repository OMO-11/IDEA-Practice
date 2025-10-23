package RPG_Life;

public class Main {
    public static void main(String[] args) throws Exception {
        Player p1 = new Player();
        //save_Player p = new save_Player();
        //p.saveFile(p1);
        reload_Player p2 = new reload_Player();
        p2.reloadFile("Moliza");
    }
}
