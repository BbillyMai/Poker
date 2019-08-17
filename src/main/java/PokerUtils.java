
import javax.swing.text.html.parser.Entity;
import java.util.*;
import java.util.stream.IntStream;

public class PokerUtils {

    private final char[] nums = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};

    private PokerComparator pokerComparator = new PokerComparator();

    public List<Poker> toPoker(String str) {
        List<Poker> pokers = new ArrayList<>();
        String[] split = str.split(" ");
        IntStream.range(0, split.length).forEach(i -> {
            Poker poker = new Poker();
            poker.setCard(split[i]);
            poker.setNumber(getNumsIndex(split[i].charAt(0)));
            poker.setType(split[i].charAt(1));
            pokers.add(poker);
        });
        pokers.sort(pokerComparator);
        return pokers;
    }


    public String comparedCard(List<Poker> pokers1, List<Poker> pokers2) {

        List<PokerCard> pokerCards1 = calculatePokerNum(pokers1);
        List<PokerCard> pokerCards2 = calculatePokerNum(pokers2);

        for (int i = 0; i < pokerCards1.size(); i++) {
            int result = pokerCards1.get(i).getCount() - pokerCards2.get(i).getCount();
            if (result > 0) {
                return buildCard(pokers1);
            } else if (result < 0) {
                return buildCard(pokers2);
            } else {
                int result1 = pokerCards1.get(i).getNumber() - pokerCards2.get(i).getNumber();
                if (result1 > 0) {
                    return buildCard(pokers1);
                } else if (result1 < 0) {
                    return buildCard(pokers2);
                } else if (i == pokers1.size() - 1) {
                    return "draw";
                }
            }
        }
        return null;
    }

    private int getNumsIndex(char ch) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    private String buildCard(List<Poker> pokers) {
        StringBuilder stringBuffer = new StringBuilder();
        for (Poker aPoker : pokers) {
            stringBuffer.append(aPoker.getCard()).append(" ");
        }
        return stringBuffer.substring(0, stringBuffer.length() - 1);
    }

    private List<PokerCard> calculatePokerNum(List<Poker> pokers) {

        List<PokerCard> pokerCards = new ArrayList<>();
        Set<Integer> sets = new HashSet<>();

        for (Poker poker : pokers) {
            if (!sets.contains(poker.getNumber())) {
                sets.add(poker.getNumber());
                PokerCard pokerCard = new PokerCard();
                pokerCard.setNumber(poker.getNumber());
                pokerCard.setCount(1);
                pokerCards.add(pokerCard);
            } else {
                for (PokerCard pokerCard : pokerCards) {
                    if (pokerCard.getNumber() == poker.getNumber()) {
                        pokerCard.setCount(pokerCard.getCount() + 1);
                    }
                }
            }
        }
        pokerCards.sort(new PokerCardComparator());
        return pokerCards;
    }
}
