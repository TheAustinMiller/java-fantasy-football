public class Main {
    public static void main(String[] args) {
        QB qb = new QB();
        WR wr = new WR();
        RB rb = new RB();
        TE te = new TE();
        Player[] quarterbacks = QB.getTop30();
        for (int lcv = 0; lcv < 30; lcv++) {
            System.out.println(quarterbacks[lcv].toString());
        }
        Player[] widereceivers = WR.getTop30();
        for (int lcv = 0; lcv < 30; lcv++) {
            System.out.println(widereceivers[lcv].toString());
        }
        Player[] tightends = TE.getTop30();
        for (int lcv = 0; lcv < 30; lcv++) {
            System.out.println(tightends[lcv].toString());
        }
        Player[] runningbacks = RB.getTop30();
        for (int lcv = 0; lcv < 30; lcv++) {
            System.out.println(runningbacks[lcv].toString());
        }
    }
}
