import zoogame.users.Admin;
import zoogame.users.Player;

public class Main {
    public static void main(String[] args) {

        Admin admin = Admin.getInstance();
        admin.manageGame();

        Player player = new Player();
        player.manageGame();


    }


}