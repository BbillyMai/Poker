
import java.util.*;
import java.util.stream.IntStream;

public class PokerUtils {

    private final char[] Nums = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};
    private final char[] Types = {'C', 'D', 'H', 'S'};
    private final int Single = 1;
    private final int OnePair = 2;
    private final int TwoPair = 3;
    private final int Straight = 4;
    private final int Flush = 5;
    private final int Hulu = 6;
    private final int FourCards = 7;
    private final int StraightFlush = 8;

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
            if (pokersLevel1 == Flush && pokersLevel2 == Flush) {
                char pokersType1 = pokers1.get(0).getType();
                int index1 = getTypesIndex(pokersType1);
                char pokersType2 = pokers2.get(0).getType();
                int index2 = getTypesIndex(pokersType2);
                return comparedNumber(pokers1, pokers2, index1, index2);
            } else if (pokersLevel1 == Hulu && pokersLevel2 == Hulu) {
                return comparedHule(pokers1, pokers2);
            } else if (pokersLevel1 == FourCards && pokersLevel2 == FourCards) {
                return comparedFourCards(pokers1, pokers2);
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
        }

        return buildCard(bestPokers);
    }

    private String comparedFourCards(List<Poker> pokers1, List<Poker> pokers2) {
        Map<Integer, Integer> map1 = statisticsPoker(pokers1);
        Map<Integer, Integer> map2 = statisticsPoker(pokers2);
        int number1 = 0;
        int number2 = 0;
        for (Map.Entry<Integer, Integer> entry : map1.entrySet()) {
            if (entry.getValue() == 4) {
                number1 = entry.getKey();
            }
        }
        for (Map.Entry<Integer, Integer> entry : map2.entrySet()) {
            if (entry.getValue() == 4) {
                number2 = entry.getKey();
            }
        }
        return comparedNumber(pokers1, pokers2, number1, number2);
    }

    private String comparedHule(List<Poker> pokers1, List<Poker> pokers2) {
        Map<Integer, Integer> map1 = statisticsPoker(pokers1);
        Map<Integer, Integer> map2 = statisticsPoker(pokers2);
        int number1 = 0;
        int number2 = 0;
        for (Map.Entry<Integer, Integer> entry : map1.entrySet()) {
            if (entry.getValue() == 3) {
                number1 = entry.getKey();
            }
        }
        for (Map.Entry<Integer, Integer> entry : map2.entrySet()) {
            if (entry.getValue() == 3) {
                number2 = entry.getKey();
            }
        }
        return comparedNumber(pokers1, pokers2, number1, number2);
    }

    private String comparedNumber(List<Poker> pokers1, List<Poker> pokers2, int number1, int number2) {
        if (number1 > number2) {
            return buildCard(pokers1);
        } else if (number1 < number2) {
            return buildCard(pokers2);
        } else {
            return "draw";
        }
    }

    private int getTypesIndex(char ch) {
        for (int i = 0; i < Types.length; i++) {
            if (Types[i] == ch) {
                return i;
            }
        }
        return -1;
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

        if (isStraight(pokers) && isFlush(pokers)) {
            return StraightFlush;
        }

        if (isFourCard(pokers)) {
            return FourCards;
        }

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

    private boolean isFourCard(List<Poker> pokers) {
        Map<Integer, Integer> map = statisticsPoker(pokers);

        if (map.size() == 2) {
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() == 4) {
                    return true;
                }
            }
        }
        return false;
    }


    private boolean isHulu(List<Poker> pokers) {
        Map<Integer, Integer> map = statisticsPoker(pokers);

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
            }
        }
        return sum == 4;
    }

    private Map<Integer, Integer> statisticsPoker(List<Poker> pokers) {
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
        return map;
    }
}
