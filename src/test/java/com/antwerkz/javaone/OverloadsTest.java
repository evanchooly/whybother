package com.antwerkz.javaone;

import org.testng.annotations.Test;

class OverloadsTest {
    @Test
    public void testBar() {
        Overloads.bar();
        Overloads.bar("John");
        Overloads.bar("John", 12);
    }

}