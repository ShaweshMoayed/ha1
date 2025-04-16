package htw.berlin.prog2.ha1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Retro calculator")
class CalculatorTest {

    @Test
    @DisplayName("should display result after adding two positive multi-digit numbers")
    void testPositiveAddition() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "40";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display result after getting the square root of two")
    void testSquareRoot() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressUnaryOperationKey("√");

        String expected = "1.41421356";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when dividing by zero")
    void testDivisionByZero() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressBinaryOperationKey("/");
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when drawing the square root of a negative number")
    void testSquareRootOfNegative() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressNegativeKey();
        calc.pressUnaryOperationKey("√");

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should not allow multiple decimal dots")
    void testMultipleDecimalDots() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(1);
        calc.pressDotKey();
        calc.pressDigitKey(7);
        calc.pressDotKey();
        calc.pressDigitKey(8);

        String expected = "1.78";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }


    //TODO hier weitere Tests erstellen


    //Teilaufgabe 1
    @Test
    @DisplayName("sollte nummer zu Prozent umwandeln")
    void testPercentFunction() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(9); //Erste Ziffer
        calc.pressDigitKey(8); //Zweite Ziffer
        calc.pressUnaryOperationKey("%");

        String expected = "0.98"; //Erwartetes Ergebnis
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    //Teilaufgabe 2


    //Bug 1

    @Test
    @DisplayName("C-Taste darf beim ersten Drücken nicht Rechenoperation löschen")
    void testClearKeyOnlyResetsScreenOnce() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(6);             // 6 eingeben
        calc.pressBinaryOperationKey("+"); // +
        calc.pressDigitKey(4);             // 4 eingeben

        calc.pressClearKey();              // Einmal C drücken sollte nur Bildschirm löschen und nicht die Rechenlogik

        calc.pressDigitKey(2);             // 2 eingeben
        calc.pressEqualsKey();             // 6 + 2 = 8 (statt der ursprünglichen 6 + 4)

        String expected = "8";             // Erwartetes Ergebnis
        String actual = calc.readScreen(); // Tatsächliches Ergebnis

        assertEquals(expected, actual);    // Test schlägt fehl, weil C zu viel zurücksetzt
    }

    //Bug 2

    @Test
    @DisplayName("Mehrfaches Drücken der =-Taste sollte letzte Operation wiederholen")
    void testRepeatedEqualsKey() {
        Calculator calc = new Calculator(); // Taschenrechner starten

        calc.pressDigitKey(5);             // 5 eingeben
        calc.pressBinaryOperationKey("+"); // +
        calc.pressDigitKey(7);             // 7 eingeben
        calc.pressEqualsKey();             // 5 + 7 = 12

        calc.pressEqualsKey();             // 12 + 7 = 19

        String expected = "19";            // Erwartetes Ergebnis
        String actual = calc.readScreen(); // Tatsächliches Ergebnis

        assertEquals(expected, actual);    // Test schlägt fehl, weil Wiederholung der letzten Operation nicht klappt
    }



}

