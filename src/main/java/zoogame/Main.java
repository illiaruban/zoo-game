package zoogame;

import zoogame.users.Admin;
import zoogame.users.Player;

public class Main {
    public static void main(String[] args) {


        while(true) {
            Admin admin = Admin.getInstance();
            admin.manageGame();

            Player player = new Player();
            player.manageGame();
        }


        //TODO: balance the income for the day
        //TODO:add print balance function
        //TODO: add print visitors count
    }


}