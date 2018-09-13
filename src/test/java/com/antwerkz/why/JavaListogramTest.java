package com.antwerkz.why;

import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static java.lang.Thread.sleep;

@Test
public class JavaListogramTest {
    public void time() throws InterruptedException {
        JavaListogram list = new JavaListogram();

        add(list, "first", "second");
        add(list, "third", "fourth", "fifth");
        list.remove("fourth");
        list.add("sixth");
        list.remove("second");
        sleep(1000);
        for (Integer i = 1; i <= 7; i++) {
            list.add(i.toString());
            sleep(1000);
        }
        for (Integer i = 1; i <= 7; i++) {
            list.remove(i.toString());
        }

        list.histogram();
    }

    private void add(List<String> list, String... adds) throws InterruptedException {
        Collections.addAll(list, adds);
        sleep(1000);
    }
}
