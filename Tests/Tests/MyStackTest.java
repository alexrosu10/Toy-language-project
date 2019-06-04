package Tests;

import Model.Utils.MyStack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {
    private MyStack<Integer> exe;

    @BeforeEach
    void setUp() {
        exe=new MyStack();
        exe.push(2);
        exe.push(1);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void pop() {
        Assertions.assertEquals((int) exe.pop(),1);
    }

    @Test
    void isEmpty() {
        Assertions.assertFalse(exe.isEmpty());
    }
}