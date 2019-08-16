import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

        List<Poker> biggerPokers = new ArrayList<>();
        for (int i = 0; i < pokers1.size(); i++) {
            int result = pokerComparator.compare(pokers1.get(i), pokers2.get(i));
            if (result < 0) {
                biggerPokers = pokers1;
                break;
            }
            if (result > 0) {
                biggerPokers = pokers2;
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
}
