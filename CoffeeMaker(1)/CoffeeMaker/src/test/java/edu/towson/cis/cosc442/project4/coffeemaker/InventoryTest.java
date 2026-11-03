package edu.towson.cis.cosc442.project4.coffeemaker;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class InventoryTest {
    private Inventory myInventory;
    private Recipe myRecipe;

    @BeforeEach
    void setUp() {
        myInventory = new Inventory();

        myRecipe = new Recipe();
        myRecipe.setAmtCoffee(5);
        myRecipe.setAmtMilk(5);
        myRecipe.setAmtSugar(5);
        myRecipe.setAmtChocolate(5);
    }

    @AfterEach
    void tearDown() {
        myInventory = null;
        myRecipe = null;
    }

    @ParameterizedTest
    @CsvSource({ // all should be >= 5 to be valid, these are all invalid
            "0, 0, 0, 0, false", // Invalid
            "0, 5, 5, 5, false", // invalid coffee
            "5, 0, 5, 5, false", // invalid milk
            "5, 5, 0, 5, false", // invalid sugar
            "5, 5, 5, 0, false", // invalid chocolate
    })
    void testEnoughIngredients_Invalid(int coffee, int milk, int sugar, int chocolate) {
        // Arrange
        myInventory.setCoffee(coffee);
        myInventory.setMilk(milk);
        myInventory.setSugar(sugar);
        myInventory.setChocolate(chocolate);

        // Act, assert
        assertFalse(myInventory.enoughIngredients(myRecipe));
    }

    @Test
    void testEnoughIngredients_Valid() {
        // Arrange
        myInventory.setCoffee(5);
        myInventory.setMilk(5);
        myInventory.setSugar(5);
        myInventory.setChocolate(5);

        // Act, assert
        assertTrue(myInventory.enoughIngredients(myRecipe));
    }

    @ParameterizedTest
    @CsvSource({
            "-1, 0", // Below boundary
            "0, 0", // Boundary
            "1, 1",
            "5, 5",
    })
    void testSetChocolate_Boundary(int num, int expected) {
        // Arrange
        myInventory.setChocolate(num);
        // Act, assert
        assertEquals(expected, myInventory.getChocolate());
    }

    @ParameterizedTest
    @CsvSource({
            "-1, 0",
            "0, 0",
            "1, 1",
            "5, 5",
    })
    void testSetCoffee_Boundary(int num, int expected) {
        // Arrange
        myInventory.setCoffee(num);
        // Act, assert
        assertEquals(expected, myInventory.getCoffee());
    }

    @ParameterizedTest
    @CsvSource({
            "-1, 0",
            "0, 0",
            "1, 1",
            "5, 5",
    })
    void testSetMilk_Boundary(int num, int expected) {
        // Arrange
        myInventory.setMilk(num);
        // Act, assert
        assertEquals(expected, myInventory.getMilk());
    }

    @ParameterizedTest
    @CsvSource({
            "-1, 0",
            "0, 0",
            "1, 1",
            "5, 5",
    })
    void testSetSugar_Boundary(int num, int expected) {
        // Arrange
        myInventory.setSugar(num);
        // Act, assert
        assertEquals(expected, myInventory.getSugar());
    }
}
