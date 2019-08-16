import java.util.Comparator;

public class PokerComparator implements Comparator<Poker> {

    @Override
    public int compare(Poker p1, Poker p2) {
        return p2.getNumber() - p1.getNumber();
    }

    public int compare(Integer integer1, Integer integer2) {
        return integer2 - integer1;
    }
}
