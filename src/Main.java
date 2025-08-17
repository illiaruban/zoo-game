import zoogame.users.Admin;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Admin admin = new Admin();
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        /*
        admin.addNewAnimal(scanner);
        admin.printAnimalList();
        admin.deleteAnimal(scanner);
        admin.printAnimalList();
        * */

        admin.printDomainList();

    }


}