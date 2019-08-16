public class PokerUtils {

    private final static char[] nums = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};

    public static Poker toPoker(String str) {
        Poker poker = new Poker();
        poker.setCard(str);
        poker.setNumber(getNumsIndex(str.charAt(0)));
        poker.setType(str.charAt(1));
        return poker;
    }

    public static String comparedCard(Poker p1, Poker p2) {
        return PokerComparator.compare(p1, p2);
    }

    private static int getNumsIndex(char ch) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == ch) {
                return i;
            }
        }
        return -1;
    }
}
