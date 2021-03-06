package base_elements;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Macros {
    private double protein;
    private double fat;
    private double carbo;

    @Override
    public String toString() {
        return "base_elements.Macros{" +
                "protein=" + protein +
                ", fat=" + fat +
                ", carbo=" + carbo +
                '}';
    }
}
