package com.nightingale.bf.service.execute;

import com.nightingale.bf.ctrl.Helper;
import com.nightingale.bf.model.spec.BoolSpec;
import org.springframework.stereotype.Service;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class BoolExecutor extends BoolSpec implements Executor {
    @Override
    public String execute(String code, Deque<Integer> input) {
        StringBuilder output = new StringBuilder();
        List<Integer> tape = new LinkedList<>();
        tape.add(0);
        String opt = optimize(code);

        int countIn = 0;
        int nextIn = getNextIn(input);
        int countOut = 0;
        int nextOut = 0;

        int pointer = 0;
        int length = opt.length();
        for (int i = 0; i < length; i++) {
            switch (opt.charAt(i)) {
                case '>':
                    pointer++;
                    if (pointer == tape.size()) {
                        tape.add(0);
                    }
                    break;
                case '<':
                    pointer--;
                    if (pointer == -1) {
                        tape.add(0,0);
                        pointer = 0;
                    }
                    break;
                case '+':
                    tape.set(pointer, (tape.get(pointer) ^ 1));
                    break;
                case ',':
                    tape.set(pointer, nextIn & 1);
                    nextIn >>= 1;
                    countIn++;
                    if (countIn == BITS_NUM) {
                        countIn = 0;
                        nextIn = getNextIn(input);
                    }
                    break;
                case ';':
                    nextOut >>= 1;
                    nextOut ^= (2 ^ tape.get(pointer)) << (BITS_NUM - 1);
                    nextOut ^= 1 << BITS_NUM;
                    countOut++;
                    if (countOut == BITS_NUM) {
                        output.append((char)nextOut);
                        countOut = 0;
                        nextOut = 0;
                    }
                    break;
                case '[':
                    if (tape.get(pointer) == 0) {
                        i = Helper.closingBracket(opt,i);
                    }
                    break;
                case ']':
                    if (tape.get(pointer) != 0) {
                        i = Helper.openingBracket(opt,i);
                    }
                    break;
                default:
                    break;
            }
        }
        return output.toString();
    }

    private int getNextIn(Deque<Integer> input) {
        return ofNullable(input.poll()).orElse(0);
    }
}
