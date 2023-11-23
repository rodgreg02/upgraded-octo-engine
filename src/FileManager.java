import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

    static public boolean createDatabase() {
        try {
            File dataStore = new File("userbase.txt");
            if (dataStore.createNewFile()) {
                System.out.println("No previous database found. Creating a new one.");
                return true;
            } else {
                System.out.println("Loading previous database...");
                return false;
            }
        } catch (IOException e) {
            System.out.println("Something went terribly wrong");
            return false;
        }
    }

    static public boolean writeDatabase(String toWrite) {
        try {
            FileWriter fw = new FileWriter("userbase.txt",false);
            toWrite = toWrite.replaceAll("\\[","");
            toWrite = toWrite.replaceAll("]","");
            toWrite = toWrite.replaceAll("," , "\n");
            fw.write(toWrite);
            fw.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return true;
    }

    static public ArrayList<Player> readDatabase() {
        try {
            File obj = new File("userbase.txt");
            Scanner reader = new Scanner(obj);
            ArrayList<Player> players = new ArrayList<>();
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                line = line.replaceAll("\\[","");
                line = line.replaceAll("\\]","");
                String[] splitString = line.split("/");
                Player player = new Player(splitString[0], splitString[1], Integer.parseInt(splitString[2]));
                players.add(player);
            }
            reader.close();
            return players;
        } catch (IOException e) {
            System.out.println("Something went wrong reading previous tasks.");
        }
        return null;
    }
}

