import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Builder
public class Product implements Comparable, Cloneable {
    private final static double FAT_KCAL = 9.0;
    private final static double CARBO_KCAL = 4.0;
    private final static double PROTEIN_KCAL = 4.0;

    private String name;
    private boolean isCountable;
    private double kcal;
    private Macros macros;

    public Product updateKcal() {
        kcal = FAT_KCAL * macros.getFat() + CARBO_KCAL * macros.getCarbo() + PROTEIN_KCAL * macros.getProtein();
        return this;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Product) {
            Product otherProduct = (Product) o;
            return otherProduct.name.compareTo(this.name);
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return isCountable == product.isCountable &&
                Double.compare(product.kcal, kcal) == 0 &&
                Objects.equals(name, product.name) &&
                Objects.equals(macros, product.macros);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isCountable, kcal, macros);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", macros=" + macros +
                '}';
    }
}
