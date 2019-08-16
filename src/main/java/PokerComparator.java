
public class PokerComparator {

    public static String compare(Poker p1, Poker p2) {
        if (p1.getNumber() > p2.getNumber()) {
            return p1.getCard();
        } else if (p1.getNumber() < p2.getNumber()) {
            return p2.getCard();
        } else {
            return "draw";
        }
    }
}
