package core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FileHandlerTest {

    // private static void checkCalc(FileHandler calc, double... operands) {
    //     Assertions.assertEquals(operands.length, calc.getOperandCount(), "Wrong operand count");
    //     for (int i = 0; i < operands.length; i++) {
    //         Assertions.assertEquals(operands[i], calc.peekOperand(i), "Wrong value at #" + i + " of operand stack");
    //     }
    // }

    // @Test
    // public void testCalc() {
    //     checkCalc(new FileHandler());
    //     checkCalc(new FileHandler(1.0), 1.0);
    //     checkCalc(new FileHandler(3.14, 1.0), 1.0, 3.14);
    // }

    // @Test
    // public void testPushOperand() {
    //     FileHandler calc = new FileHandler();
    //     calc.pushOperand(1.0);
    //     checkCalc(calc, 1.0);
    //     calc.pushOperand(3.14);
    //     checkCalc(calc, 3.14, 1.0);
    // }

    // @Test
    // public void testPeekOperand() {
    //     FileHandler calc = new FileHandler(1.0, 3.14);
    //     Assertions.assertEquals(3.14, calc.peekOperand());
    //     Assertions.assertThrows(IllegalArgumentException.class, () -> new FileHandler().peekOperand());
    // }

    // @Test
    // public void testPeekOperandN() {
    //     FileHandler calc = new FileHandler(1.0, 3.14);
    //     Assertions.assertEquals(3.14, calc.peekOperand(0));
    //     Assertions.assertEquals(1.0, calc.peekOperand(1));
    //     Assertions.assertThrows(IllegalArgumentException.class, () -> calc.peekOperand(2));
    // }

    // @Test
    // public void testPopOperand() {
    //     FileHandler calc = new FileHandler(1.0, 3.14);
    //     Assertions.assertEquals(3.14, calc.popOperand());
    //     checkCalc(calc, 1.0);
    //     Assertions.assertEquals(1.0, calc.popOperand());
    //     checkCalc(calc);
    // }

    // @Test
    // public void testPopOperand_emptyStack() {
    //     Assertions.assertThrows(IllegalStateException.class, () -> new FileHandler().popOperand());
    // }

    // @Test
    // public void testPerformOperation1() {
    //     FileHandler calc = new FileHandler(1.0);
    //     Assertions.assertEquals(-1.0, calc.performOperation(n -> -n));
    //     checkCalc(calc, -1.0);
    // }

    // @Test
    // public void testPerformOperation1_emptyOperandStack() {
    //     Assertions.assertThrows(IllegalStateException.class, () -> new FileHandler().performOperation(n -> -n));
    // }


    // @Test
    // public void testPerformOperation2() {
    //     FileHandler calc = new FileHandler(1.0, 3.0);
    //     Assertions.assertEquals(-2.0, calc.performOperation((n1, n2) -> n1 - n2));
    //     checkCalc(calc, -2.0);
    // }

    // @Test
    // public void testPerformOperation2_lessThanTwoOperands() {
    //     Assertions.assertThrows(IllegalStateException.class, () -> new FileHandler(1.0).performOperation((n1, n2) -> n1 - n2));
    //     Assertions.assertThrows(IllegalStateException.class, () -> new FileHandler().performOperation((n1, n2) -> n1 - n2));
    // }

    // @Test
    // public void testSwap() {
    //     FileHandler calc = new FileHandler(1.0, 3.14);
    //     checkCalc(calc, 3.14, 1.0);
    //     calc.swap();
    //     checkCalc(calc, 1.0, 3.14);
    //     calc.swap();
    //     checkCalc(calc, 3.14, 1.0);
    // }

    // @Test
    // public void testSwap_lessThanTwoOperands() {
    //     Assertions.assertThrows(IllegalStateException.class, () -> new FileHandler(1.0).swap());
    //     Assertions.assertThrows(IllegalStateException.class, () -> new FileHandler().swap());
    // }

    // @Test
    // public void testDup() {
    //     FileHandler calc = new FileHandler(1.0, 3.14);
    //     Assertions.assertEquals(3.14, calc.popOperand());
    //     checkCalc(calc, 1.0);
    //     Assertions.assertEquals(1.0, calc.popOperand());
    //     checkCalc(calc);
    // }

    // @Test
    // public void testDup_emptyOperandStack() {
    //     Assertions.assertThrows(IllegalStateException.class, () -> new FileHandler().dup());
    // }
}
