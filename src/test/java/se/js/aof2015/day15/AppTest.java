package se.js.aof2015.day15;

import org.junit.Assert;
import org.junit.Test;
import se.js.aof2015.Util;

import java.util.List;

public class AppTest {
    @Test
    public void givenIngrediantData_whenFindingTotalValuePart2_shouldReturnTotalValue() {
        Assert.assertEquals(57600000, App.getProductOfAllPropertiesPart2(data, 100));
    }

    @Test
    public void givenIngrediantData_whenFindingTotalValuePart1_shouldReturnTotalValue() {
        Assert.assertEquals(62842880, App.getProductOfAllPropertiesPart1(data, 100));
    }

    @Test
    public void givenPossibleRecipe_whenFindingTotalValue_shouldReturnTotalValue() {
        var ingredients = App.Ingrediant.from(Util.input(data));
        Assert.assertEquals(2, ingredients.size());
        var recipe = List.of(44, 56);

        Assert.assertEquals(62842880, App.getProductOfAllPropertiesPart1(recipe, ingredients));
    }

    @Test
    public void givenPossibleRecipe_whenFindingSumOfPropertyValue_shouldReturnTotalSumOfPropertyValues() {
        var ingredients = App.Ingrediant.from(Util.input(data));
        Assert.assertEquals(2, ingredients.size());
        var recipe = List.of(44, 56);

        Assert.assertEquals(68, App.getSumOfPropertValues(recipe, ingredients, App.Ingrediant::capacity));
        Assert.assertEquals(80, App.getSumOfPropertValues(recipe, ingredients, App.Ingrediant::durability));
        Assert.assertEquals(152, App.getSumOfPropertValues(recipe, ingredients, App.Ingrediant::flavor));
        Assert.assertEquals(76, App.getSumOfPropertValues(recipe, ingredients, App.Ingrediant::texture));
    }
    @Test
    public void givenPossibleRecipeGeneratesNegativeNumber_whenFindingSumOfPropertyValue_shouldReturnZero() {
        var ingredients = App.Ingrediant.from(Util.input(data));
        Assert.assertEquals(2, ingredients.size());
        var recipe = List.of(94, 6);

        Assert.assertEquals(0, App.getSumOfPropertValues(recipe, ingredients, App.Ingrediant::capacity));
        Assert.assertEquals(0, App.getSumOfPropertValues(recipe, ingredients, App.Ingrediant::durability));
        Assert.assertEquals(552, App.getSumOfPropertValues(recipe, ingredients, App.Ingrediant::flavor));
        Assert.assertEquals(276, App.getSumOfPropertValues(recipe, ingredients, App.Ingrediant::texture));
    }
    @Test
    public void givenTargetValueAndSize_whenGeneratingPossibleRecipe_shouldReturnCorrectNumberOfPermutationAndSumOfValues() {
        var targetValue = 4;
        var size = 3;
        var possibleRecipes = App.generatePossibleRecipe(targetValue, size);
        Assert.assertEquals(15, possibleRecipes.size());
        Assert.assertFalse(possibleRecipes.stream().anyMatch(l -> l.stream().reduce(0, Integer::sum) != targetValue));
    }


    String data = """
            Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8
            Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3
            """;
}
