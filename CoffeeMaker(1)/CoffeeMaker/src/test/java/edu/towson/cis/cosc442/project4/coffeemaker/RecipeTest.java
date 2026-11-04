package edu.towson.cis.cosc442.project4.coffeemaker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RecipeTest {
    private Recipe myRecipe;

    @BeforeEach
    void setUp() {
        myRecipe = new Recipe();
        myRecipe.setName("Recipe1");
        myRecipe.setAmtChocolate(5);
        myRecipe.setAmtCoffee(5);
        myRecipe.setAmtMilk(5);
        myRecipe.setAmtSugar(5);
        myRecipe.setPrice(25);
    }

    @AfterEach
    void tearDown() {
        myRecipe = null;
    }

    // Duplicate recipe checking
    @Test
    void testEquals() {

    }

    @Test
    void testGetAmtChocolate() {
        // Act, assert
        assertEquals(5, myRecipe.getAmtChocolate());
    }

    @Test
    void testGetAmtCoffee() {
        // Act, assert
        assertEquals(5, myRecipe.getAmtCoffee());
    }

    @Test
    void testGetAmtMilk() {
        // Act, assert
        assertEquals(5, myRecipe.getAmtMilk());
    }

    @Test
    void testGetAmtSugar() {
        // Act, assert
        assertEquals(5, myRecipe.getAmtSugar());
    }

    @Test
    void testGetName() {
        // Act, assert
        assertEquals("Recipe1", myRecipe.getName());
    }

    @Test
    void testGetPrice() {
        // Act, assert
        assertEquals(25, myRecipe.getPrice());
    }

    @ParameterizedTest
    @CsvSource({ // >= 0, else 0
            "-1, 0",
            "0, 0",
            "1, 1",
            "5, 5",
    })
    void testSetAmtChocolate_Boundary(int num, int expected) {
        // Arrange
        myRecipe.setAmtChocolate(num);
        // Act, assert
        assertEquals(expected, myRecipe.getAmtChocolate());
    }

    @ParameterizedTest
    @CsvSource({ // >= 0, else 0
            "-1, 0",
            "0, 0",
            "1, 1",
            "5, 5",
    })
    void testSetAmtCoffee_Boundary(int num, int expected) {
        // Arrange
        myRecipe.setAmtCoffee(num);
        // Act, assert
        assertEquals(expected, myRecipe.getAmtCoffee());
    }

    @ParameterizedTest
    @CsvSource({ // >= 0, else 0
            "-1, 0",
            "0, 0",
            "1, 1",
            "5, 5",
    })
    void testSetAmtMilk_Boundary(int num, int expected) {
        // Arrange
        myRecipe.setAmtMilk(num);
        // Act, assert
        assertEquals(expected, myRecipe.getAmtMilk());
    }

    @ParameterizedTest
    @CsvSource({ // >= 0, else 0
            "-1, 0",
            "0, 0",
            "1, 1",
            "5, 5",
    })
    void testSetAmtSugar_Boundary(int num, int expected) {
        // Arrange
        myRecipe.setAmtSugar(num);
        // Act, assert
        assertEquals(expected, myRecipe.getAmtSugar());
    }

    @ParameterizedTest
    @CsvSource({ // >= 0, else 0
            "-1, 0",
            "0, 0",
            "1, 1",
            "25, 25",
    })
    void testSetPrice_Boundary(int num, int expected) {
        // Arrange
        myRecipe.setPrice(num);
        // Act, assert
        assertEquals(expected, myRecipe.getPrice());
    }

    @Test
    void testToString() {
        assertEquals(myRecipe.getName(), myRecipe.toString());
    }

    @Test
    void testEquals_NullName() {
        // Arrange
        Recipe unnamed = new Recipe();
        // Act, assert
        assertFalse(unnamed.equals(myRecipe));
    }

    @Test
    void testEquals_DifferentNames() {
        // Arrange
        Recipe recipe2 = new Recipe();
        recipe2.setName("Recipe2");
        // Act, assert
        assertFalse(myRecipe.equals(recipe2));
    }

    @Test
    void testEquals_SameNames() {
        // Arrange
        Recipe recipe2 = new Recipe();
        recipe2.setName("Recipe1");
        // Act, assert
        assertTrue(myRecipe.equals(recipe2));
    }

    @Test
    void testEquals_ValidEqualsNull() {
        // Arrange, act, assert
        assertFalse(myRecipe.equals(new Recipe()));
    }

    @Test
    void testEquals_BothNamesNull() {
        // Arrange, act, assert
        assertFalse(new Recipe().equals(new Recipe()));
    }

}
