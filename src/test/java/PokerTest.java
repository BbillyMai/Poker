import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PokerTest {

    @Test
    public void should_8H_is_bigger_given_3H_And_8H(){
        String card1 = "8H";
        String card2 = "3H";
        Poker poker1 = PokerUtils.toPoker(card1);
        Poker poker2 = PokerUtils.toPoker(card2);

        String result = PokerUtils.comparedCard(poker1,poker2);

        assertEquals("8H",result);
    }

    @Test
    public void should_TH_is_bigger_given_TH_And_8H(){
        String card1 = "8H";
        String card2 = "TH";
        Poker poker1 = PokerUtils.toPoker(card1);
        Poker poker2 = PokerUtils.toPoker(card2);

        String result = PokerUtils.comparedCard(poker1,poker2);

        assertEquals("TH",result);
    }

    @Test
    public void should_AH_is_bigger_given_AH_And_TH(){
        String card1 = "AH";
        String card2 = "TH";
        Poker poker1 = PokerUtils.toPoker(card1);
        Poker poker2 = PokerUtils.toPoker(card2);

        String result = PokerUtils.comparedCard(poker1,poker2);

        assertEquals("AH",result);
    }
}
