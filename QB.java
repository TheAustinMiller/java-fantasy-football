import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class QB {
    final int PASSING_YARDS = 25; //25 YARDS PER POINT
    final int PASSING_TOUCHDOWNS = 4;
    final int INTERCEPTIONS = -1;
    final int RUSHING_YARDS = 10; // 10 YARDS PER POINT
    final int RUSHING_TOUCHDOWNS = 6;
    final int FUMBLES = -2;

    static ArrayList<Player> qbs = new ArrayList<>();

    ArrayList<Integer> points = new ArrayList<>();
    static ArrayList<Integer> rank = new ArrayList<>();
    ArrayList<String> name = new ArrayList<>();
    ArrayList<Integer> cmp = new ArrayList<>(); //completions
    ArrayList<Integer> att = new ArrayList<>(); //attempts
    ArrayList<Double> pct = new ArrayList<>(); //completion percentage
    ArrayList<Integer> yards = new ArrayList<>(); //yards
    ArrayList<Double> avYards = new ArrayList<>(); //average yards
    ArrayList<Integer> td = new ArrayList<>();
    ArrayList<Integer> interceptions = new ArrayList<>();
    ArrayList<Integer> sacks = new ArrayList<>();
    ArrayList<Integer> rushAtt = new ArrayList<>(); // rushing attempts
    ArrayList<Integer> rushYards = new ArrayList<>();
    ArrayList<Integer> rushTD = new ArrayList<>(); // rushing touchdowns
    ArrayList<Integer> fumbles = new ArrayList<>();
    ArrayList<Integer> games = new ArrayList<>();
    ArrayList<Double> fantasyPoints = new ArrayList<>();
    ArrayList<Double> fantasyPointsPerGame = new ArrayList<>();
    ArrayList<Double> rost = new ArrayList<>();
    public QB() {
        try {
            File file = new File("C:\\Users\\ajthe\\IdeaProjects\\fantasyFootball\\" +
                    "src\\ff\\FantasyPros_Fantasy_Football_Statistics_QB.csv");
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                line = line.replaceAll("\"", "");
                line = line.replaceAll("%", "");


                String[] player = line.split(",");
                rank.add(Integer.parseInt(player[0]));
                name.add(player[1]);
                cmp.add(Integer.parseInt(player[2]));
                att.add(Integer.parseInt(player[3]));
                pct.add(Double.parseDouble(player[4]));
                yards.add(Integer.parseInt(player[5]));
                avYards.add(Double.parseDouble(player[6]));
                td.add(Integer.parseInt(player[7]));
                interceptions.add(Integer.parseInt(player[8]));
                sacks.add(Integer.parseInt(player[9]));
                rushAtt.add(Integer.parseInt(player[10]));
                rushYards.add(Integer.parseInt(player[11]));
                rushTD.add(Integer.parseInt(player[12]));
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

    public void calculatePoints() {
        for (int i = 0; i < rank.size(); i++) {
            int sum = 0;
            sum += yards.get(i) / PASSING_YARDS; //1 point per PASSING_YARDS
            sum += (td.get(i) * PASSING_TOUCHDOWNS);
            sum += interceptions.get(i) * INTERCEPTIONS;
            sum += rushYards.get(i) / RUSHING_YARDS; //1 point per RUSHING_YARDS
            sum += (rushTD.get(i) * RUSHING_TOUCHDOWNS);
            sum += fumbles.get(i) * FUMBLES;
            points.add(sum);
        }
    }

    public void displayPoints() {
        for (int i = 0; i < rank.size(); i++) {
            System.out.println(name.get(i) + " - " + points.get(i));
        }
    }

    public void mapPlayers() {
        for (int i = 0; i < rank.size(); i++) {
            Player q = new Player(name.get(i), points.get(i) , "QB");
            qbs.add(q);
        }
    }

    public void printPlayers() {
        for (int i = 0; i < qbs.size(); i++) {
            System.out.println(qbs.get(i).getName() + " - " + qbs.get(i).getPoints());
        }
    }

    public static Player[] getTop30() {
        Player[] arr = new Player[30];
        for (int i = 0; i < 30; i++) {
            int biggest = 0;
            int bigIndex = 0;
            Player bigPlayer = qbs.get(i);
            for (int j = 0; j < rank.size() - 30; j++) {
                if (biggest < qbs.get(j).getPoints()) {
                    biggest = qbs.get(j).getPoints();
                    bigPlayer = qbs.get(j);
                    bigIndex = j;
                }
            }
            qbs.remove(bigIndex);
            arr[i] = bigPlayer;
        }

        return arr;
    }
}
