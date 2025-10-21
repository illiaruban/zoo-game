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
        //TODO: make sure that animals with 2 times are fed 2 times
        //TODO:make sure that animals that are already fed for this stage are not fed again
    }


}