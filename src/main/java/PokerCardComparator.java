import java.util.Comparator;

public class PokerCardComparator implements Comparator<PokerCard> {
    @Override
    public int compare(PokerCard o1, PokerCard o2) {
        if (o1.getCount() != o2.getCount()) {
            return o2.getCount() - o1.getCount();
        } else {
            return o2.getNumber() - o1.getNumber();
        }
    }
}
