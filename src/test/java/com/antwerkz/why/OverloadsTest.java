package com.antwerkz.why;

import org.testng.annotations.Test;

@Test
class OverloadsTest {
    public void testBar() {
        Overloads.INSTANCE.bar();
        Overloads.INSTANCE.bar("John");
        Overloads.INSTANCE.bar("John", 12);

        Overloads.bar();
        Overloads.bar("John");
        Overloads.bar("John", 12);
    }

}

