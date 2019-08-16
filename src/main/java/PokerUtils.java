
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

        Map<Integer, Integer> pokerIntegerMap1 = calculatePokerNum(pokers1);
        Map<Integer, Integer> pokerIntegerMap2 = calculatePokerNum(pokers2);

        List<Poker> biggerPokers = new ArrayList<>();

        int index = 0;
        for (Map.Entry<Integer, Integer> entity1 : pokerIntegerMap1.entrySet()) {
            index++;
            for (Map.Entry<Integer, Integer> entity2 : pokerIntegerMap2.entrySet()) {
                if (entity1.getValue() > entity2.getValue()) {
                    biggerPokers = pokers1;
                } else if (entity1.getValue() < entity2.getValue()) {
                    biggerPokers = pokers2;
                } else {
                    int result = pokerComparator.compare(entity1.getKey(), entity2.getKey());
                    if (result < 0) {
                        biggerPokers = pokers1;
                    } else if (result > 0) {
                        biggerPokers = pokers2;
                    } else if (index == pokers1.size()) {
                        return "draw";
                    }
                }
                break;
            }
        }

        return buildCard(biggerPokers);
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

    private Map<Integer, Integer> calculatePokerNum(List<Poker> pokers) {
        Map<Integer, Integer> pokerIntegerMap = new HashMap<>();

        for (Poker poker : pokers) {
            if (!pokerIntegerMap.containsKey(poker.getNumber())) {
                pokerIntegerMap.put(poker.getNumber(), 1);
            } else {
                Integer value = pokerIntegerMap.get(poker.getNumber());
                value += 1;
                pokerIntegerMap.remove(poker.getNumber());
                pokerIntegerMap.put(poker.getNumber(), value);
            }
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(pokerIntegerMap.entrySet());

        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        Iterator<Map.Entry<Integer, Integer>> iterator = list.iterator();
        Map.Entry<Integer, Integer> tmpEntry = null;
        Map<Integer, Integer> sortedMap = new LinkedHashMap<>();
        while (iterator.hasNext()) {
            tmpEntry = iterator.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }
}
