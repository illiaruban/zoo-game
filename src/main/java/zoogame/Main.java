package zoogame;

import zoogame.db.Database;
import zoogame.users.Admin;
import zoogame.users.Player;

public class Main {
    public static void main(String[] args) throws Exception {

        Database db = Database.getInstance();
        System.out.println("DB connected: " + db.getConnection().isValid(2));


        while(true) {
            Admin admin = Admin.getInstance();
            admin.manageGame();

            Player player = new Player();
            player.manageGame();
        }

    }


}