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
}
