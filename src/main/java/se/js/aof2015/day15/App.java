package se.js.aof2015.day15;

import se.js.aof2015.Util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

interface Neighborhood {
    int getCount(Class c);
}
interface Living {
    Living next(Neighborhood n); // next generation

    char getCharRepresentation();
}

public class App {
    public static void main(String[] args) {
        // Part1 13882464
        // Par2 11171160
        System.out.println(part1());
        System.out.println(part2());
    }

    static int part2() {
        return getProductOfAllPropertiesPart2(App.data, 100);
    }

    static int part1() {
        return getProductOfAllPropertiesPart1(App.data, 100);
    }

    static int getProductOfAllPropertiesPart2(String data, int totAmountOfIngrediant) {
        var ingredients = App.Ingrediant.from(Util.input(data));
        var possibleRecipe = generatePossibleRecipe(totAmountOfIngrediant, ingredients.size());
        return possibleRecipe.stream()
                .filter(recipe -> getSumOfPropertValues(recipe, ingredients, Ingrediant::calories) == 500)
                .map(recipe -> getProductOfAllPropertiesPart1(recipe, ingredients))
                .max(Comparator.naturalOrder())
                .orElse(0);
    }

    static int getProductOfAllPropertiesPart1(String data, int totAmountOfIngrediant) {
        var ingredients = App.Ingrediant.from(Util.input(data));
        var possibleRecipe = generatePossibleRecipe(totAmountOfIngrediant, ingredients.size());
        return possibleRecipe.stream()
                .map(recipe -> getProductOfAllPropertiesPart1(recipe, ingredients))
                .max(Comparator.naturalOrder())
                .orElse(0);
    }

    static int getProductOfAllPropertiesPart1(List<Integer> recipe, List<Ingrediant> ingrediants) {
        return List.of(
                getSumOfPropertValues(recipe, ingrediants, Ingrediant::capacity),
                getSumOfPropertValues(recipe, ingrediants, Ingrediant::durability),
                getSumOfPropertValues(recipe, ingrediants, Ingrediant::flavor),
                getSumOfPropertValues(recipe, ingrediants, Ingrediant::texture)
        ).stream().reduce(1, (v1, v2) -> v1 * v2);
    }

    static int getSumOfPropertValues(List<Integer> recipe, List<Ingrediant> ingrediants, Function<Ingrediant, Integer> property) {
        assert recipe.size() == ingrediants.size() : "Recipe missmatch the list ingrediants";

        int sum = 0;
        for (int i = 0; i < recipe.size(); i++) {
            sum += (recipe.get(i) * property.apply(ingrediants.get(i)));
        }
        return sum > 0 ? sum : 0;
    }

    record Ingrediant(String name, int capacity, int durability, int flavor, int texture, int calories) {
        static Ingrediant from(String input) {
            var parts = input.replaceAll(",", "").split(" ");
            return new Ingrediant(parts[0], Integer.parseInt(parts[2]), Integer.parseInt(parts[4]), Integer.parseInt(parts[6]), Integer.parseInt(parts[8]), Integer.parseInt(parts[10]));
        }

        static List<Ingrediant> from(List<String> input) {
            return input.stream()
                    .map(Ingrediant::from)
                    .toList();
        }
    }

    static List<List<Integer>> generatePossibleRecipe(int targetValue, int maxLength) {
        return generatePossibleRecipe(new ArrayList<>(), targetValue, targetValue, maxLength);
    }

    static List<List<Integer>> generatePossibleRecipe(List<Integer> template, int restValue, int targetValue, int maxLength) {
        var result = new ArrayList<List<Integer>>();
        var templateAccValue = template.stream().reduce(0, Integer::sum);

        for (int i = restValue; i >= 0; i--) {
            var recipe = new ArrayList<Integer>(template);
            recipe.add(i);

            if (recipe.size() == maxLength && templateAccValue + i == targetValue) {
                result.add(recipe);
            } else if (recipe.size() < maxLength) {
                result.addAll(generatePossibleRecipe(recipe, restValue - i, targetValue, maxLength));
            }
        }
        return result;
    }

    static String data = """
                Sprinkles: capacity 5, durability -1, flavor 0, texture 0, calories 5
                PeanutButter: capacity -1, durability 3, flavor 0, texture 0, calories 1
                Frosting: capacity 0, durability -1, flavor 4, texture 0, calories 6
                Sugar: capacity -1, durability 0, flavor 0, texture 2, calories 8            
            """;
}
