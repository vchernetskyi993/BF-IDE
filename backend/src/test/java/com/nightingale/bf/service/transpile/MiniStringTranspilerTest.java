package com.nightingale.bf.service.transpile;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MiniStringTranspilerTest {
    private final Transpiler transpiler = new MiniStringTranspiler();

    @Test
    public void shouldTranspileToHighLevel() {
        assertEquals(
            "*p += 72\n" +
                "printChar(*p)\n" +
                "*p += 29\n" +
                "printChar(*p)\n" +
                "*p += 7\n" +
                "printChar(*p)\n" +
                "printChar(*p)\n" +
                "*p += 3\n" +
                "printChar(*p)\n" +
                "*p += 189\n" +
                "printChar(*p)\n" +
                "*p += 244\n" +
                "printChar(*p)\n" +
                "*p += 55\n" +
                "printChar(*p)\n" +
                "*p += 24\n" +
                "printChar(*p)\n" +
                "*p += 3\n" +
                "printChar(*p)\n" +
                "*p += 250\n" +
                "printChar(*p)\n" +
                "*p += 248\n" +
                "printChar(*p)\n" +
                "*p += 189\n" +
                "printChar(*p)\n",
            transpiler.toHighLevel("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++." +
                "+++++++++++++++++++++++++++++.+++++++..+++." +
                "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++." +
                "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++." +
                "+++++++++++++++++++++++++++++++++++++++++++++++++++++++." +
                "++++++++++++++++++++++++.+++." +
                "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++." +
                "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++." +
                "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.")
        );
    }
}
