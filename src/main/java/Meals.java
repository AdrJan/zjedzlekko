import java.util.*;

public class Meals {

    static Set<Product> baseProducts = new HashSet<>();

    static {
        baseProducts.add(Product
                .builder()
                .macros(Macros.builder().carbo(0.4).fat(5.8).protein(7.5).build())
                .isCountable(true)
                .name("egg")
                .build()
                .updateKcal());
        baseProducts.add(Product
                .builder()
                .macros(Macros.builder().carbo(29.3).fat(1.8).protein(3.75).build())
                .isCountable(true)
                .name("roll")
                .build()
                .updateKcal());
        baseProducts.add(Product
                .builder()
                .macros(Macros.builder().carbo(2).fat(2).protein(16).build())
                .isCountable(true)
                .name("chicken breast cold cut")
                .build()
                .updateKcal());
    }

    public Set<List<Portion>> getMeals() {
        Macros desiredMacro = Macros.builder()
                .carbo(90)
                .fat(15)
                .protein(25)
                .build();
        return new MealGenerator().generateMeals(desiredMacro, baseProducts);
    }

    public static void main(String... args) {
        Set<List<Portion>> meals = new Meals().getMeals();
        for(List<Portion> meal : meals) {
            for(Portion product : meal)
                System.out.println(product);
            System.out.println(MealGenerator.getMealMacro(meal));
        }
    }
}
