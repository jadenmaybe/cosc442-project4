package edu.towson.cis.cosc442.project4.coffeemaker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CoffeeMakerTest {

    private CoffeeMaker coffeeMaker;
    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;
    private Recipe recipe4;
    private Recipe recipe5;

    @BeforeEach
    void setUp() {
        coffeeMaker = new CoffeeMaker();
        recipe1 = new Recipe();
        recipe1.setName("recipe1");
        recipe1.setPrice(50);
        recipe1.setAmtCoffee(3);
        recipe1.setAmtMilk(1);
        recipe1.setAmtSugar(0);
        recipe1.setAmtChocolate(0);

        recipe2 = new Recipe();
        recipe3 = new Recipe();
        recipe4 = new Recipe();
        recipe5 = new Recipe();
    }

    @AfterEach
    void tearDown() {
        coffeeMaker = null;
        recipe1 = null;
        recipe2 = null;
        recipe3 = null;
        recipe4 = null;
        recipe5 = null;
    }

    @Test
    void testAddRecipe_Valid() {
        // Arrange, act
        boolean result = coffeeMaker.addRecipe(recipe1);
        // Assert
        assertTrue(result);
    }

    @Test
    void testAddRecipe_HandleDuplicate() {
        // Arrange
        coffeeMaker.addRecipe(recipe1);
        // Act, assert
        assertFalse(coffeeMaker.addRecipe(recipe1));
    }

    @Test
    public void testAddRecipe_Full() {
        // Arrange
        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.addRecipe(recipe2);
        coffeeMaker.addRecipe(recipe3);
        coffeeMaker.addRecipe(recipe4);

        // Act, assert
        assertFalse(coffeeMaker.addRecipe(recipe5));
    }

    @Test
    public void testCheckInventory() {
        // Act, assert
        assertNotNull(coffeeMaker.checkInventory());
    }

    @Test
    void deleteRecipe_Valid() {
        // Arrange
        coffeeMaker.addRecipe(recipe1);
        // Act, assert
        assertTrue(coffeeMaker.deleteRecipe(recipe1));
        assertNotEquals("recipe1", coffeeMaker.getRecipeForName("recipe1").getName());
    }

    @Test
    public void testDeleteRecipe_Invalid() {
        // Act, assert
        assertFalse(coffeeMaker.deleteRecipe(recipe1));
    }

    @Test
    public void testDeleteRecipe_Null() {
        // Act, assert
        assertFalse(coffeeMaker.deleteRecipe(null));
    }

    @Test
    public void testEditRecipe_Valid() {
        // Arrange
        coffeeMaker.addRecipe(recipe1);
        recipe2.setName("recipe2");

        // Act, assert
        assertTrue(coffeeMaker.editRecipe(recipe1, recipe2));
        assertNotEquals("recipe1", coffeeMaker.getRecipeForName("recipe1").getName());
    }

    @Test
    public void testEditRecipe_InvalidRecipe() {
        // Assert
        assertFalse(coffeeMaker.editRecipe(recipe1, recipe2));
    }

    // amtCoffee >= 0, amtMilk >= 0, amtSugar >= 0, amtChocolate >= 0, expected
    // (non-negative values)
    @ParameterizedTest
    @CsvSource({
            "2, 2, 2, 2, true", // All valid positive
            "0, 1, 1, 1, true", // Coffee boundary (coffee = 0)
            "1, 0, 1, 1, true", // Milk boundary (milk = 0)
            "1, 1, 0, 1, true", // Sugar boundary (sugar = 0)
            "1, 1, 1, 0, true", // Chocolate boundary (chocolate = 0)
            "-1, 1, 0, 1, false", // Negative coffee boundary
            "1, -1, 0, 1, false", // Negative milk boundary
            "1, 1, -1, 1, false", // Negative sugar boundary
            "1, 1, 0, -1, false" // Negative chocolate boundary
    })
    public void testAddInventory(int coffee, int milk, int sugar, int chocolate, boolean expected) {
        // Act, assert
        assertEquals(expected, coffeeMaker.addInventory(coffee, milk, sugar, chocolate));
    }

    @Test
    public void testAddInventory_CorrectIngredientTotal() {
        // Arrange
        Inventory inventory = coffeeMaker.checkInventory();
        // Act, assert
        assertTrue(coffeeMaker.addInventory(1, 2, 3, 4));
        assertEquals(16, inventory.getCoffee());
        assertEquals(17, inventory.getMilk());
        assertEquals(18, inventory.getSugar());
        assertEquals(19, inventory.getChocolate());
    }

    @Test
    public void testGetRecipeForName_Valid() {
        // Arrange
        coffeeMaker.addRecipe(recipe1);
        // Act
        Recipe name = coffeeMaker.getRecipeForName("recipe1");
        // Assert
        assertNotNull(name);
        assertEquals("recipe1", name.getName());
    }

    @Test
    public void testGetRecipeForName_Invalid() {
        // Arrange, act
        Recipe name = coffeeMaker.getRecipeForName("recipe100");
        // Assert
        assertNotNull(name);
        assertNull(name.getName());
    }

    @Test
    void testGetRecipes() {

    }

    @Test
    public void testMakeCoffee_Valid() {
        // Arrange
        coffeeMaker.addRecipe(recipe1);
        // Act, assert
        assertEquals(0, coffeeMaker.makeCoffee(recipe1, 50));
    }

    @Test
    public void testMakeCoffee_InsufficientFunds() {
        // Arrange
        coffeeMaker.addRecipe(recipe1);
        // Act, assert
        assertEquals(5, coffeeMaker.makeCoffee(recipe1, 5));
    }

    @Test
    public void testMakeCoffee_InsufficientIngredients() {
        // Arrange
        recipe1.setAmtCoffee(100);
        coffeeMaker.addRecipe(recipe1);

        // Act, assert: cannot be made, so the entire amount is returned
        assertEquals(50, coffeeMaker.makeCoffee(recipe1, 50));
    }

    @Test
    void testGetRecipes_Valid() {
        // Act
        Recipe[] recipes = coffeeMaker.getRecipes();
        // Assert
        assertNotNull(recipes);
        assertEquals(4, recipes.length);
    }

}
