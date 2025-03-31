import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RB {
    final int RUSHING_YARDS = 10; // 10 yards per point
    final int RUSHING_TOUCHDOWNS = 6;
    final int RECEIVING_YARDS = 7; // 7 yards per point
    final int RECEIVING_TOUCHDOWNS = 6;
    final int FUMBLES = -2;

    static ArrayList<Player> rbs = new ArrayList<>();
    ArrayList<Integer> points = new ArrayList<>();
    static ArrayList<Integer> rank = new ArrayList<>();
    ArrayList<String> name = new ArrayList<>();
    ArrayList<Integer> attempts = new ArrayList<>();
    ArrayList<Integer> yards = new ArrayList<>();
    ArrayList<Double> yardsPerRush = new ArrayList<>();
    ArrayList<Integer> longestRun = new ArrayList<>();
    ArrayList<Integer> twentyPlus = new ArrayList<>();
    ArrayList<Integer> rushTD = new ArrayList<>();
    ArrayList<Integer> receptions = new ArrayList<>();
    ArrayList<Integer> targets = new ArrayList<>();
    ArrayList<Integer> receivingYards = new ArrayList<>();
    ArrayList<Double> yardsPerCatch = new ArrayList<>();
    ArrayList<Integer> receivingTouchdowns = new ArrayList<>();
    ArrayList<Integer> fumbles = new ArrayList<>();
    ArrayList<Integer> games = new ArrayList<>();
    ArrayList<Double> fantasyPoints = new ArrayList<>();
    ArrayList<Double> fantasyPointsPerGame = new ArrayList<>();
    ArrayList<Double> rost = new ArrayList<>();

    public RB() {
        try {
            File file = new File("C:\\Users\\ajthe\\IdeaProjects\\fantasyFootball\\" +
                    "src\\ff\\FantasyPros_Fantasy_Football_Statistics_RB.csv");
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                line = line.replaceAll("\"", "");
                line = line.replaceAll("%", "");


                String[] player = line.split(",");
                rank.add(Integer.parseInt(player[0]));
                name.add(player[1]);
                attempts.add(Integer.parseInt(player[2]));
                yards.add(Integer.parseInt(player[3]));
                yardsPerRush.add(Double.parseDouble(player[4]));
                longestRun.add(Integer.parseInt(player[5]));
                twentyPlus.add(Integer.parseInt(player[6]));
                rushTD.add(Integer.parseInt(player[7]));
                receptions.add(Integer.parseInt(player[8]));
                targets.add(Integer.parseInt(player[9]));
                receivingYards.add(Integer.parseInt(player[10]));
                yardsPerCatch.add(Double.parseDouble(player[11]));
                receivingTouchdowns.add(Integer.parseInt(player[12]));
                fumbles.add(Integer.parseInt(player[13]));
                games.add(Integer.parseInt(player[14]));
                fantasyPoints.add(Double.parseDouble(player[15]));
                fantasyPointsPerGame.add(Double.parseDouble(player[16]));
                rost.add(Double.parseDouble(player[17]));
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

    public static Player[] getTop30() {
        Player[] arr = new Player[30];
        for (int i = 0; i < 30; i++) {
            int biggest = 0;
            int bigIndex = 0;
            Player bigPlayer = rbs.get(i);
            for (int j = 0; j < rank.size() - 30; j++) {
                if (biggest < rbs.get(j).getPoints()) {
                    biggest = rbs.get(j).getPoints();
                    bigPlayer = rbs.get(j);
                    bigIndex = j;
                }
            }
            rbs.remove(bigIndex);
            arr[i] = bigPlayer;
        }
        return arr;
    }

    public void mapPlayers() {
        for (int i = 0; i < rank.size(); i++) {
            Player q = new Player(name.get(i), points.get(i), "RB");
            rbs.add(q);
        }
    }

    public void printPlayers() {
        for (int i = 0; i < rbs.size(); i++) {
            System.out.println(rbs.get(i).getName() + " - " + rbs.get(i).getPoints());
        }
    }

    public void calculatePoints() {
        for (int i = 0; i < rank.size(); i++) {
            int sum = 0;
            sum += yards.get(i) / RUSHING_YARDS;
            sum += rushTD.get(i) * RUSHING_TOUCHDOWNS;
            sum += receivingYards.get(i) / RECEIVING_YARDS;
            sum += receivingTouchdowns.get(i) * RECEIVING_TOUCHDOWNS;
            sum += fumbles.get(i) * FUMBLES;
            points.add(sum);
        }
    }

    public void displayPoints() {
        for (int i = 0; i < rank.size(); i++) {
            System.out.println(name.get(i) + " - " + points.get(i));
        }
    }
}
