import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean authentication = false;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Player> players = new ArrayList<>();
        if (!FileManager.createDatabase()) {
            players = FileManager.readDatabase();
        }
        System.out.println(Color.CLEAR_CONSOLE);
        Splash.displayInitalPage();
        Player oldPlayer = new Player("", "", 0);
        while (!authentication) {
            System.out.println("1)Register a new account\t\t2)Login\t\t0)Quit :(");
            String input0 = scanner.next();
            switch (input0) {
                case "1":
                    scanner.reset();
                    System.out.println("Insert your new username and after Enter, your password:");
                    String username = scanner.next();
                    for (int i = 0; i < players.size(); i++) {
                        String[] login = players.get(i).toString().split("/");
                        if (username.equals(login[0])) {
                            System.out.println("Username is taken. Try a more unique one.");
                        } else {
                            String password = scanner.next();
                            Player newPlayer = new Player(username, password);
                            players.add(newPlayer);
                            FileManager.writeDatabase(players.toString().trim());
                            System.out.println("Success, redirecting to login screen, and insert your credentials.");
                        }
                        break;
                    }
                case "2":
                    System.out.println("Insert your username:");
                    Scanner scanner2 = new Scanner(System.in);
                    String usernameCheck = scanner2.next();
                    for (int i = 0; i < players.size(); i++) {
                        String[] login = players.get(i).toString().trim().split("/");
                        if (usernameCheck.equals(login[0])) {
                            System.out.println("Enter your password:");
                            String passwordCheck = scanner.next();
                            boolean auth = (passwordCheck.equals(login[1]));
                            if (auth) {
                                oldPlayer = new Player(login[0], login[1], Integer.parseInt(login[2]));
                            }
                            System.out.println((auth) ? "Success, logged in. Welcome back, " + login[0] :
                                    "Wrong password.");
                            authentication = auth;

                        } else {
                            System.out.println("Username not found. Try registering!");
                        }
                    }
                    break;
                case "0":
                    return;
                default:
                    Splash.displayError();
                    break;
            }
        }
        boolean quit = false;
        while (!quit) {
            if (authentication) {
                Splash.displaySplashPage();
                System.out.println("Welcome back, " + oldPlayer.getUsername() + ", You have " + oldPlayer.getMoney() + " V Bucks");
                switch (scanner.next()) {
                    case "1":
                        try {
                            oldPlayer.setMoney(Blackjack.play(oldPlayer.getMoney()));
                            players.set(players.indexOf(oldPlayer),oldPlayer);
                            FileManager.writeDatabase(players.toString().trim());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "2":
                        break;
                    case "q":
                        Splash.displayBye();
                        quit = true;
                        break;
                    default:
                        Splash.displayError();
                        break;

                }
            }
        }

    }
}