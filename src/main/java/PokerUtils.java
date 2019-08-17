
import java.util.*;
import java.util.stream.IntStream;

public class PokerUtils {

    private final char[] Nums = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};
    private final int Single = 1;
    private final int OnePair = 2;
    private final int TwoPair = 3;
    private final int Straight = 4;
    private final int Flush = 5;
    private final int Hulu = 6;

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

        int pokersLevel1 = calculatePokerLevel(pokers1);
        int pokersLevel2 = calculatePokerLevel(pokers2);

        List<Poker> bestPokers = new ArrayList<>();

        if (pokersLevel1 > pokersLevel2) {
            bestPokers = pokers1;
        } else if (pokersLevel1 < pokersLevel2) {
            bestPokers = pokers2;
        } else {
            for (int i = 0; i < pokers1.size(); i++) {
                int result = pokerComparator.compare(pokers1.get(i), pokers2.get(i));
                if (result > 0) {
                    return buildCard(pokers2);
                } else if (result < 0) {
                    return buildCard(pokers1);
                } else if (i == pokers1.size() - 1) {
                    return "draw";
                }
            }
        }

        return buildCard(bestPokers);
    }

    private int getNumsIndex(char ch) {
        for (int i = 0; i < Nums.length; i++) {
            if (Nums[i] == ch) {
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

    private int calculatePokerLevel(List<Poker> pokers) {

        Set<Integer> sets = new HashSet<>();
        int level = Single;

        if (isHulu(pokers)) {
            return Hulu;
        }

        if (isFlush(pokers)) {
            return Flush;
        }
        if (isStraight(pokers)) {
            return Straight;
        }

        for (Poker poker : pokers) {
            if (!sets.contains(poker.getNumber())) {
                sets.add(poker.getNumber());
            } else {
                level++;
            }
        }
        return level;
    }

    private boolean isHulu(List<Poker> pokers) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Poker poker : pokers) {
            if (!map.keySet().contains(poker.getNumber())) {
                map.put(poker.getNumber(), 1);
            } else {
                int value = map.get(poker.getNumber());
                value++;
                map.remove(poker.getNumber());
                map.put(poker.getNumber(), value);
            }
        }
        if (map.size() == 2) {
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() == 2 || entry.getValue() == 3) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isStraight(List<Poker> pokers) {

        int start = pokers.get(0).getNumber();
        int sum = 0;
        for (int i = 1; i < pokers.size(); i++) {
            if (start - 1 == pokers.get(i).getNumber()) {
                sum++;
                start = pokers.get(i).getNumber();
            }
        }
        return sum == 4;
    }

    private boolean isFlush(List<Poker> pokers) {
        char start = pokers.get(0).getType();
        int sum = 0;
        for (int i = 1; i < pokers.size(); i++) {
            if (start == pokers.get(i).getType()) {
                sum++;
                start = pokers.get(i).getType();
            }
        }
        return sum == 4;
    }
}
