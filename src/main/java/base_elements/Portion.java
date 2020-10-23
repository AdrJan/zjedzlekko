package base_elements;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Portion implements Comparable, Cloneable {
    private Product product;
    private double quantity;

    public Portion(Product product, double quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Macros getMacros() {
        return Macros
                .builder()
                .carbo(quantity * product.getMacros().getCarbo())
                .protein(quantity * product.getMacros().getProtein())
                .fat(quantity * product.getMacros().getFat())
                .build();
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Portion) {
            return ((Portion) o).getProduct().getName().compareTo(this.getProduct().getName());
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Portion portion = (Portion) o;
        return Double.compare(portion.quantity, quantity) == 0 &&
                Objects.equals(product, portion.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity);
    }

    @Override
    public Object clone() {
        Portion clone;
        try {
            clone = (Portion) super.clone();
            clone.setProduct(this.getProduct());
            clone.setQuantity(this.getQuantity());
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException();
        }
        return clone;
    }

    @Override
    public String toString() {
        return "base_elements.Portion{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
