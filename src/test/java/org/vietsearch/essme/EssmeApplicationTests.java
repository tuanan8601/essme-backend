package org.vietsearch.essme;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Arrays;

@SpringBootTest
class EssmeApplicationTests {

    @Test
    void contextLoads() {
        String[] arr = "pham trung hieu".split(" ");
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < arr.length; i++) {
            sb.append(Character.toUpperCase(arr[i].charAt(0)))
                    .append(arr[i].substring(1)).append(" ");
        }
        System.out.println( sb.toString().trim());
    }

}
