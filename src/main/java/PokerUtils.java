public class PokerUtils {

    public static Poker toPoker(String str) {
        Poker poker = new Poker();
        poker.setCard(str);
        poker.setNumber(str.charAt(0));
        poker.setType(str.charAt(1));
        return poker;
    }

    public static String comparedCard(Poker p1, Poker p2) {
        return PokerComparator.compare(p1, p2);
    }
}
