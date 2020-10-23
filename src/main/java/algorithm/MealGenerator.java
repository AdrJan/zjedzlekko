package algorithm;

import base_elements.Macros;
import base_elements.Portion;
import base_elements.Product;

import java.util.*;

public class MealGenerator {

    private static final double MACRO_MIN_RATIO = 0.95;
    private static final double MACRO_MAX_RATIO = 1.05;

    private static final int MACRO_OVERFLOW = -1;
    private static final int MACRO_NOT_SUFF = 0;
    private static final int MACRO_IN_RANGE = 1;

    public Set<List<Portion>> generateMeals(Macros desiredMacro, Set<Product> baseProducts) {
        Set<List<Portion>> possibleMeals = new HashSet<>();
        addProduct(null, new ArrayList<>(), possibleMeals, baseProducts, desiredMacro);
        return possibleMeals;
    }

    private void addProduct(Portion portion, List<Portion> portionList, Set<List<Portion>> portionLists, Set<Product> allProducts, Macros desiredMacro) {
        increasePortion(portion, portionList);
        List<Portion> clonedPortionList = getClonedPortionList(portionList);
        int macroStatus = isDesired(getMealMacro(portionList), desiredMacro);
        if (macroStatus == MACRO_IN_RANGE)
            portionLists.add(clonedPortionList);
        else if (macroStatus == MACRO_NOT_SUFF)
            for (Product baseProduct : allProducts)
                addProduct(new Portion(baseProduct, 0.2), clonedPortionList, portionLists, allProducts, desiredMacro);
    }

    private void increasePortion(Portion newPortion, List<Portion> portions) {
        if (newPortion != null) {
            int i = getProductIndex(newPortion, portions);
            if (i >= 0)
                portions.get(i).setQuantity(portions.get(i).getQuantity() + newPortion.getQuantity());
            else
                portions.add(newPortion);
        }
    }

    private List<Portion> getClonedPortionList(List<Portion> portionList) {
        List<Portion> clonedPortionList = new ArrayList<>();
        for (Portion portion : portionList) clonedPortionList.add((Portion) portion.clone());
        return clonedPortionList;
    }

    int getProductIndex(Portion portion, List<Portion> portions) {
        String productName = portion.getProduct().getName();
        for (int i = 0; i < portions.size(); i++)
            if (productName.equals(portions.get(i).getProduct().getName()))
                return i;
        return -1;
    }

    public static Macros getMealMacro(List<Portion> productList) {
        Macros macros = Macros.builder().build();
        for (Portion portion : productList) {
            macros.setProtein(macros.getProtein() + portion.getProduct().getMacros().getProtein() * portion.getQuantity());
            macros.setCarbo(macros.getCarbo() + portion.getProduct().getMacros().getCarbo() * portion.getQuantity());
            macros.setFat(macros.getFat() + portion.getProduct().getMacros().getFat() * portion.getQuantity());
        }
        return macros;
    }

    private int isDesired(Macros currMacro, Macros desiredMacro) {
        double carbRatio = currMacro.getCarbo() / desiredMacro.getCarbo();
        double proteinRatio = currMacro.getProtein() / desiredMacro.getProtein();
        double fatRatio = currMacro.getFat() / desiredMacro.getFat();

        if (carbRatio > MACRO_MAX_RATIO || proteinRatio > MACRO_MAX_RATIO || fatRatio > MACRO_MAX_RATIO)
            return MACRO_OVERFLOW;

        return (carbRatio >= MACRO_MIN_RATIO) && (proteinRatio >= MACRO_MIN_RATIO) && (fatRatio >= MACRO_MIN_RATIO)
                ? MACRO_IN_RANGE
                : MACRO_NOT_SUFF;
    }
}
