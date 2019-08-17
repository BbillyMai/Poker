
import java.util.*;
import java.util.stream.IntStream;

public class PokerUtils {

    private final char[] Nums = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};
    private final char[] Types = {'C', 'D', 'H', 'S'};

    private final int Single = PokerLevelEnum.HIGH_CARD.ordinal();
    private final int OnePair = PokerLevelEnum.ONE_PAIR.ordinal();
    private final int TwoPair = PokerLevelEnum.TWO_PAIR.ordinal();
    private final int ThreeCards = PokerLevelEnum.THREE_KING.ordinal();
    private final int Straight = PokerLevelEnum.STRAIGHT.ordinal();
    private final int Flush = PokerLevelEnum.FLUSH.ordinal();
    private final int Hulu = PokerLevelEnum.FULL_HOUSE.ordinal();
    private final int FourCards = PokerLevelEnum.FOUR_KING.ordinal();
    private final int StraightFlush = PokerLevelEnum.STRAIGHT_FLUSH.ordinal();

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
                return comparedFlush(pokers1, pokers2);
            } else if (pokersLevel1 == Hulu && pokersLevel2 == Hulu) {
                return comparedHule(pokers1, pokers2);
            } else if (pokersLevel1 == FourCards && pokersLevel2 == FourCards) {
                return comparedFourCards(pokers1, pokers2);
            } else if (pokersLevel1 == ThreeCards && pokersLevel2 == ThreeCards) {
                return comparedHule(pokers1, pokers2);
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

    private String comparedFlush(List<Poker> pokers1, List<Poker> pokers2) {
        char pokersType1 = pokers1.get(0).getType();
        int index1 = getTypesIndex(pokersType1);
        char pokersType2 = pokers2.get(0).getType();
        int index2 = getTypesIndex(pokersType2);
        return comparedNumber(pokers1, pokers2, index1, index2);
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


    private String buildCard(List<Poker> pokers) {
        StringBuilder stringBuffer = new StringBuilder();
        for (Poker aPoker : pokers) {
            stringBuffer.append(aPoker.getCard()).append(" ");
        }
        return stringBuffer.substring(0, stringBuffer.length() - 1);
    }

    private int calculatePokerLevel(List<Poker> pokers) {

        Set<Integer> sets = new HashSet<>();

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

        if (isThreeCard(pokers)) {
            return ThreeCards;
        }

        if (isTwoPair(pokers)) {
            return TwoPair;
        }

        if (isOnePair(pokers)) {
            return OnePair;
        }

        return Single;
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

    private boolean isFourCard(List<Poker> pokers) {
        Map<Integer, Integer> map = statisticsPoker(pokers);
        return map.size() == 2 && containsValue(map, 4);
    }

    private boolean isHulu(List<Poker> pokers) {
        Map<Integer, Integer> map = statisticsPoker(pokers);
        return map.size() == 2 && (containsValue(map, 2) || containsValue(map, 3));
    }

    private boolean isThreeCard(List<Poker> pokers) {
        Map<Integer, Integer> map = statisticsPoker(pokers);
        return map.size() == 3 && containsValue(map, 3);
    }

    private boolean isOnePair(List<Poker> pokers) {
        Map<Integer, Integer> map = statisticsPoker(pokers);
        return map.size() == 4 && containsValue(map, 2);
    }

    private boolean isTwoPair(List<Poker> pokers) {
        Map<Integer, Integer> map = statisticsPoker(pokers);
        return map.size() == 3 && containsValue(map, 2);
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

    private boolean containsValue(Map<Integer, Integer> map, int value) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == value) {
                return true;
            }
        }
        return false;
    }
}
