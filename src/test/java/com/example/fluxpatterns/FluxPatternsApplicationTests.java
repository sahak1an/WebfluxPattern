package com.example.fluxpatterns;

import org.junit.jupiter.api.Test;

class FluxPatternsApplicationTests {

    @Test
    void oneBitsEvenNumberTest() {
        System.out.println(oneBitsEvenNumber(8));
        System.out.println(oneBitsEvenNumber(5));
        System.out.println(oneBitsEvenNumber(10));
        System.out.println(oneBitsEvenNumber(20));
        System.out.println(oneBitsEvenNumber(11));
    }

    private boolean oneBitsEvenNumber(int number) {
        System.out.printf("Check %d --> %s%n", number, Integer.toBinaryString(number));

        int binaryOneCount;
        for (binaryOneCount = 0; number > 0; ++binaryOneCount) {
            number &= number - 1;
        }
        return binaryOneCount % 2 == 0;
    }
}
