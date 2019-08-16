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
        Collections.sort(pokers, pokerComparator);
        return pokers;
    }


    public String comparedCard(List<Poker> pokers1, List<Poker> pokers2) {
        StringBuilder stringBuffer = new StringBuilder();
        int max = 1;
        for (int i = 0; i < pokers1.size(); i++) {
            int result = pokerComparator.compare(pokers1.get(i), pokers2.get(i));
            if (result < 0) {
                break;
            }
            if (result > 0) {
                max = 2;
                break;
            }
        }
        if (max == 1) {
            for (Poker aPokers1 : pokers1) {
                stringBuffer.append(aPokers1.getCard()).append(" ");
            }
        } else {
            for (Poker aPokers2 : pokers2) {
                stringBuffer.append(aPokers2.getCard()).append(" ");
            }
        }
        return stringBuffer.substring(0, stringBuffer.length() - 1);
    }

    private int getNumsIndex(char ch) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == ch) {
                return i;
            }
        }
        return -1;
    }
}
