import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PokerTest {

    @Test
    public void should_8H_is_bigger_given_3H_And_8H() {
        PokerUtils pokerUtils = new PokerUtils();
        String card1 = "8H";
        String card2 = "3H";
        List<Poker> poker1 = pokerUtils.toPoker(card1);
        List<Poker> poker2 = pokerUtils.toPoker(card2);

        String result = pokerUtils.comparedCard(poker1, poker2);

        assertEquals("8H", result);
    }

    @Test
    public void should_TH_is_bigger_given_TH_And_8H() {
        PokerUtils pokerUtils = new PokerUtils();
        String card1 = "8H";
        String card2 = "TH";
        List<Poker> poker1 = pokerUtils.toPoker(card1);
        List<Poker> poker2 = pokerUtils.toPoker(card2);

        String result = pokerUtils.comparedCard(poker1, poker2);

        assertEquals("TH", result);
    }

    @Test
    public void should_AH_is_bigger_given_AH_And_TH() {
        PokerUtils pokerUtils = new PokerUtils();
        String card1 = "AH";
        String card2 = "TH";
        List<Poker> poker1 = pokerUtils.toPoker(card1);
        List<Poker> poker2 = pokerUtils.toPoker(card2);

        String result = pokerUtils.comparedCard(poker1, poker2);

        assertEquals("AH", result);
    }

    @Test
    public void should_3H8H_is_bigger_given_3H8H_And_4H5H() {
        PokerUtils pokerUtils = new PokerUtils();
        String card1 = "3H 8H";
        String card2 = "4H 5H";
        List<Poker> poker1 = pokerUtils.toPoker(card1);
        List<Poker> poker2 = pokerUtils.toPoker(card2);

        String result = pokerUtils.comparedCard(poker1, poker2);

        assertEquals("8H 3H", result);
    }

    @Test
    public void should_THAH_is_bigger_given_THJH_And_THAH() {
        PokerUtils pokerUtils = new PokerUtils();
        String card1 = "TH AH";
        String card2 = "TH JH";
        List<Poker> poker1 = pokerUtils.toPoker(card1);
        List<Poker> poker2 = pokerUtils.toPoker(card2);

        String result = pokerUtils.comparedCard(poker1, poker2);

        assertEquals("AH TH", result);
    }

    @Test
    public void should_4H5HTH8C9C_is_bigger_given_4H5HTH8C9C_And_4H5HTH8CAC() {
        PokerUtils pokerUtils = new PokerUtils();
        String card1 = "4H 5H TH 8C AC";
        String card2 = "4H 5H TH 8C 9C";
        List<Poker> poker1 = pokerUtils.toPoker(card1);
        List<Poker> poker2 = pokerUtils.toPoker(card2);

        String result = pokerUtils.comparedCard(poker1, poker2);

        assertEquals("AC TH 8C 5H 4H", result);
    }
}
