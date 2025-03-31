import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TE {
    final int RUSHING_YARDS = 10; //10 yards per point
    final int RUSHING_TOUCHDOWNS = 6;
    final int RECEIVING_YARDS = 7; //7 yards per point
    final int RECEIVING_TOUCHDOWNS = 6;
    final int FUMBLES = -2;

    static ArrayList<Player> tes = new ArrayList<>();
    ArrayList<Integer> points = new ArrayList<>();
    static ArrayList<Integer> rank = new ArrayList<>();
    ArrayList<String> name = new ArrayList<>();
    ArrayList<Integer> receptions = new ArrayList<>();

    ArrayList<Integer> targets = new ArrayList<>();

    ArrayList<Integer> yards = new ArrayList<>();

    ArrayList<Double> yardsPerReceptions = new ArrayList<>();

    ArrayList<Integer> longestReception = new ArrayList<>();

    ArrayList<Integer> twentyPlus = new ArrayList<>();

    ArrayList<Integer> receivingTouchdowns = new ArrayList<>();

    ArrayList<Integer> rushAttempts = new ArrayList<>();

    ArrayList<Integer> rushYards = new ArrayList<>();

    ArrayList<Integer> rushTD = new ArrayList<>();

    ArrayList<Integer> fumbles = new ArrayList<>();

    ArrayList<Integer> games = new ArrayList<>();

    ArrayList<Double> fantasyPoints = new ArrayList<>();

    ArrayList<Double> fantasyPointsPerGame = new ArrayList<>();

    ArrayList<Double> rost = new ArrayList<>();


    public TE() {
        try {
            File file = new File("C:\\Users\\ajthe\\IdeaProjects\\fantasyFootball\\" +
                    "src\\ff\\FantasyPros_Fantasy_Football_Statistics_TE.csv");
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                line = line.replaceAll("\"", "");
                line = line.replaceAll("%", "");


                String[] player = line.split(",");
                rank.add(Integer.parseInt(player[0]));
                name.add(player[1]);
                receptions.add(Integer.parseInt(player[2]));
                targets.add(Integer.parseInt(player[3]));
                yards.add(Integer.parseInt(player[4]));
                yardsPerReceptions.add(Double.parseDouble(player[5]));
                longestReception.add(Integer.parseInt(player[6]));
                twentyPlus.add(Integer.parseInt(player[7]));
                receivingTouchdowns.add(Integer.parseInt(player[8]));
                rushAttempts.add(Integer.parseInt(player[9]));
                rushYards.add(Integer.parseInt(player[10]));
                rushTD.add(Integer.parseInt(player[11]));
                fumbles.add(Integer.parseInt(player[12]));
                games.add(Integer.parseInt(player[13]));
                fantasyPoints.add(Double.parseDouble(player[14]));
                fantasyPointsPerGame.add(Double.parseDouble(player[15]));
                rost.add(Double.parseDouble(player[16]));
            }
            scanner.close();
            calculatePoints();
            mapPlayers();
            //printPlayers();
            //displayPoints();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void mapPlayers() {
        for (int i = 0; i < rank.size(); i++) {
            Player q = new Player(name.get(i), points.get(i), "TE");
            tes.add(q);
        }
    }

    public void printPlayers() {
        for (int i = 0; i < tes.size(); i++) {
            System.out.println(tes.get(i).getName() + " - " + tes.get(i).getPoints());
        }
    }

    public void calculatePoints() {
        for (int i = 0; i < rank.size(); i++) {
            int sum = 0;
            sum += rushYards.get(i) / RUSHING_YARDS;
            sum += rushTD.get(i) * RUSHING_TOUCHDOWNS;
            sum += yards.get(i) / RECEIVING_YARDS;
            sum += receivingTouchdowns.get(i) * RECEIVING_TOUCHDOWNS;
            sum += fumbles.get(i) * FUMBLES;
            points.add(sum);
        }
    }

    public static Player[] getTop30() {
        Player[] arr = new Player[30];
        for (int i = 0; i < 30; i++) {
            int biggest = 0;
            int bigIndex = 0;
            Player bigPlayer = tes.get(i);
            for (int j = 0; j < rank.size() - 30; j++) {
                if (biggest < tes.get(j).getPoints()) {
                    biggest = tes.get(j).getPoints();
                    bigPlayer = tes.get(j);
                    bigIndex = j;
                }
            }
            tes.remove(bigIndex);
            arr[i] = bigPlayer;
        }
        return arr;
    }
    public void displayPoints() {
        for (int i = 0; i < rank.size(); i++) {
            System.out.println(name.get(i) + " - " + points.get(i));
        }
    }
}
