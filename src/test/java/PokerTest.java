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

    @Test
    public void should_5H5HTH8C9C_is_bigger_given_5H5HTH8C9C_And_4H5HTH8CAC() {
        PokerUtils pokerUtils = new PokerUtils();
        String card1 = "4H 5D TH 8C AC";
        String card2 = "5H 5C TH 8C 9C";
        List<Poker> poker1 = pokerUtils.toPoker(card1);
        List<Poker> poker2 = pokerUtils.toPoker(card2);

        String result = pokerUtils.comparedCard(poker1, poker2);

        assertEquals("TH 9C 8C 5H 5C", result);
    }

    @Test
    public void should_4H_5H_TH_AC_AC_is_bigger_given_5H_5H_TH_8C_9C_And_4H_5H_TH_AC_AC() {
        PokerUtils pokerUtils = new PokerUtils();
        String card1 = "4H 5H TH AC AC";
        String card2 = "5H 5H TH 8C 9C";
        List<Poker> poker1 = pokerUtils.toPoker(card1);
        List<Poker> poker2 = pokerUtils.toPoker(card2);

        String result = pokerUtils.comparedCard(poker1, poker2);

        assertEquals("AC AC TH 5H 4H", result);
    }

    @Test
    public void should_5D_5C_TH_8C_8D_is_bigger_given_4H_5H_TH_AC_AD_And_5D_5C_TH_8C_8D() {
        PokerUtils pokerUtils = new PokerUtils();
        String card1 = "4H 5H TH AC AD";
        String card2 = "5D 5C TH 8C 8D";
        List<Poker> poker1 = pokerUtils.toPoker(card1);
        List<Poker> poker2 = pokerUtils.toPoker(card2);

        String result = pokerUtils.comparedCard(poker1, poker2);

        assertEquals("TH 8C 8D 5D 5C", result);
    }

    @Test
    public void should_4H_4S_TH_TC_AD_is_bigger_given_4H_4S_TH_TC_AD_And_5D_5C_TH_8C_8D() {
        PokerUtils pokerUtils = new PokerUtils();
        String card1 = "4H 4S TH TC AD";
        String card2 = "5D 5C TH 8C 8D";
        List<Poker> poker1 = pokerUtils.toPoker(card1);
        List<Poker> poker2 = pokerUtils.toPoker(card2);

        String result = pokerUtils.comparedCard(poker1, poker2);

        assertEquals("AD TH TC 4H 4S", result);
    }

    @Test
    public void should_3D_4C_5H_6C_7D_is_bigger_given_3D_4C_5H_6C_7D_And_5D_5C_TH_8C_8D() {
        PokerUtils pokerUtils = new PokerUtils();
        String card1 = "3D 4C 5H 6C 7D";
        String card2 = "5D 5C TH 8C 8D";
        List<Poker> poker1 = pokerUtils.toPoker(card1);
        List<Poker> poker2 = pokerUtils.toPoker(card2);

        String result = pokerUtils.comparedCard(poker1, poker2);

        assertEquals("7D 6C 5H 4C 3D", result);
    }

    @Test
    public void should_QC_JS_TD_KH_AC_is_bigger_given_3D_4C_5H_6C_7D_And_QC_JS_TD_KH_AC() {
        PokerUtils pokerUtils = new PokerUtils();
        String card1 = "QC JS TD KH AC";
        String card2 = "3D 4C 5H 6C 7D";
        List<Poker> poker1 = pokerUtils.toPoker(card1);
        List<Poker> poker2 = pokerUtils.toPoker(card2);

        String result = pokerUtils.comparedCard(poker1, poker2);

        assertEquals("AC KH QC JS TD", result);
    }

    @Test
    public void should_3D_5D_TD_KD_7D_is_bigger_given_3D_4C_5H_6C_7D_And_3D_5D_TD_KD_7D() {
        PokerUtils pokerUtils = new PokerUtils();
        String card1 = "3D 5D TD KD 7D";
        String card2 = "3D 4C 5H 6C 7D";
        List<Poker> poker1 = pokerUtils.toPoker(card1);
        List<Poker> poker2 = pokerUtils.toPoker(card2);

        String result = pokerUtils.comparedCard(poker1, poker2);

        assertEquals("KD TD 7D 5D 3D", result);
    }

    @Test
    public void should_3S_5S_TS_KS_7S_is_bigger_given_3C_TC_AC_6C_7C_And_3S_5S_TS_KS_7S() {
        PokerUtils pokerUtils = new PokerUtils();
        String card1 = "3S 5S TS KS 7S";
        String card2 = "3C TC AC 6C 7C";
        List<Poker> poker1 = pokerUtils.toPoker(card1);
        List<Poker> poker2 = pokerUtils.toPoker(card2);

        String result = pokerUtils.comparedCard(poker1, poker2);

        assertEquals("KS TS 7S 5S 3S", result);
    }

    @Test
    public void should_6D_6C_6H_3C_3D_is_bigger_given_6D_6C_6H_3C_3D_And_3D_5D_TD_KD_7D() {
        PokerUtils pokerUtils = new PokerUtils();
        String card1 = "3D 5D TD KD 7D";
        String card2 = "6D 6C 6H 3C 3D";
        List<Poker> poker1 = pokerUtils.toPoker(card1);
        List<Poker> poker2 = pokerUtils.toPoker(card2);

        String result = pokerUtils.comparedCard(poker1, poker2);

        assertEquals("6D 6C 6H 3C 3D", result);
    }

    @Test
    public void should_6D_6C_6H_3C_3D_is_bigger_given_6D_6C_6H_3C_3D_And_4D_4H_4S_7D_7C() {
        PokerUtils pokerUtils = new PokerUtils();
        String card1 = "4D 4H 4S 7D 7C";
        String card2 = "6D 6C 6H 3C 3D";
        List<Poker> poker1 = pokerUtils.toPoker(card1);
        List<Poker> poker2 = pokerUtils.toPoker(card2);

        String result = pokerUtils.comparedCard(poker1, poker2);

        assertEquals("6D 6C 6H 3C 3D", result);
    }

    @Test
    public void should_6D_6C_6H_6S_3D_is_bigger_given_6D_6C_6H_6S_3D_And_6D_6C_6H_3C_3D() {
        PokerUtils pokerUtils = new PokerUtils();
        String card1 = "6D 6C 6H 3C 3D";
        String card2 = "6D 6C 6H 6S 3D";
        List<Poker> poker1 = pokerUtils.toPoker(card1);
        List<Poker> poker2 = pokerUtils.toPoker(card2);

        String result = pokerUtils.comparedCard(poker1, poker2);

        assertEquals("6D 6C 6H 6S 3D", result);
    }

    @Test
    public void should_AC_KC_QC_JC_TC_is_bigger_given_6D_6C_6H_6S_3D_And_AC_KC_QC_JC_TC() {
        PokerUtils pokerUtils = new PokerUtils();
        String card1 = "AC KC QC JC TC";
        String card2 = "6D 6C 6H 6S 3D";
        List<Poker> poker1 = pokerUtils.toPoker(card1);
        List<Poker> poker2 = pokerUtils.toPoker(card2);

        String result = pokerUtils.comparedCard(poker1, poker2);

        assertEquals("AC KC QC JC TC", result);
    }

}
