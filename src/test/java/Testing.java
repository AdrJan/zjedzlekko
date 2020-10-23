import org.junit.Assert;
import org.junit.Test;

public class Testing {

    Product egg = Product
            .builder()
            .macros(Macros.builder().carbo(0.4).fat(5.8).protein(7.5).build())
            .isCountable(true)
            .name("egg")
            .build()
            .updateKcal();

    Product roll = Product
            .builder()
            .macros(Macros.builder().carbo(29.3).fat(1.8).protein(3.75).build())
            .isCountable(true)
            .name("roll")
            .build()
            .updateKcal();

    private final double SMALL_DELTA = 0.0001;
    private final double BIG_DELTA = 5;

    @Test
    public void checkProductValues() {
        Assert.assertEquals(egg.getMacros().getCarbo(), 0.4, SMALL_DELTA);
        Assert.assertEquals(egg.getMacros().getFat(), 5.8, SMALL_DELTA);
        Assert.assertEquals(egg.getMacros().getProtein(), 7.5, SMALL_DELTA);
        Assert.assertTrue(egg.isCountable());

        Assert.assertEquals(roll.getMacros().getCarbo(),  29.3, SMALL_DELTA);
        Assert.assertEquals(roll.getMacros().getFat(), 1.8, SMALL_DELTA);
        Assert.assertEquals(roll.getMacros().getProtein(), 3.75, SMALL_DELTA);
        Assert.assertTrue(roll.isCountable());
    }

    @Test
    public void checkKcal() {
        Assert.assertEquals(egg.getKcal(), 86, BIG_DELTA);
        Assert.assertEquals(roll.getKcal(), 148, BIG_DELTA);
    }
}
