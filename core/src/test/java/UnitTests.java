import org.junit.Test;

import us.xvicario.scryfallandroidapibinding.Card;
import us.xvicario.scryfallandroidapibinding.ScryfallAPI;

/**
 * Created by bmaurer on 2/27/2017.
 */

public class UnitTests {

    @Test
    public void test1() {
        Card card = ScryfallAPI.getCardFromMultiverse(368950);
        card.getCmc();
    }

}
