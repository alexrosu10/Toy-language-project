package Tests;

import Model.Utils.MyList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyListTest {
    private MyList<Integer> out;

    @BeforeEach
    void setUp() {
        out=new MyList<>();
        out.add(1);
        out.add(2);
        out.add(3);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void size() {
        Assertions.assertEquals(out.size(),3);
    }
}