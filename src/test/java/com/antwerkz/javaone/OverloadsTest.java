package com.antwerkz.javaone;

import org.testng.annotations.Test;

class OverloadsTest {
    @Test
    public void testBar() {
        Overloads.INSTANCE.bar();
        Overloads.INSTANCE.bar("John");
        Overloads.INSTANCE.bar("John", 12);

        Overloads.bar();
        Overloads.bar("John");
        Overloads.bar("John", 12);
    }

}