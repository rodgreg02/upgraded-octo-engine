import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean authentication = false;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Player> players = new ArrayList<>();
        if(!FileManager.createDatabase()) {
            players = FileManager.readDatabase();
        }
        System.out.println(Color.CLEAR_CONSOLE);
        Splash.displayInitalPage();
        while (!authentication){
        System.out.println("1)Register a new account\t\t2)Login");
        switch (scanner.next()){
            case "1":
                scanner.reset();
                System.out.println("Insert your new username and after Enter, your password:");
                Player newPlayer = new Player(scanner.next(),scanner.next());
                players.add(newPlayer);
                FileManager.writeDatabase(players.toString().trim());
                System.out.println("Success, press login in the initial screen, and insert your credentials.");
                break;
            case "2":
                System.out.println("Insert your username:");
                String usernameCheck = scanner.next();
                for (int i = 0; i < players.size(); i++) {
                    String[] login = players.get(i).toString().split("/");
                   if (usernameCheck.equals(login[0])) {
                       System.out.println("Enter your password:");
                       String passwordCheck = scanner.next();
                       boolean auth = (passwordCheck.equals(login[1]))? true: false;
                       if(auth){
                           Player oldPlayer = new Player(login[0],login[1],Integer.parseInt(login[2]));
                       }
                       System.out.println((auth) ? "Success, logged in. Welcome back, " + login[0] :
                               "Wrong password.");
                       authentication = auth;

                   }
                }
                break;
            default:
                System.out.println("Invalid option.");
                break;
        }}
        System.out.println("Finally");


    }
}