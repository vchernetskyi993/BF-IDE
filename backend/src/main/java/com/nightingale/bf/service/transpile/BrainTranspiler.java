package com.nightingale.bf.service.transpile;

import com.nightingale.bf.utils.Helper;
import com.nightingale.bf.service.BrainService;

public class BrainTranspiler extends BrainService implements Transpiler {
    @Override
    public String toHighLevel(String code) {
        var result = new StringBuilder();
        var spaces = 0;

        for (var tokenData : tokenize(optimize(code))) {
            result.append(Helper.spaces(spaces));

            switch (tokenData.getToken().getType()) {
                case INCR:
                    result.append(CELL)
                        .append(ADD)
                        .append(tokenData.getLength());
                    break;
                case DECR:
                    result.append(CELL)
                        .append(SUBTRACT)
                        .append(tokenData.getLength());
                    break;
                case RIGHT:
                    result.append(POINTER)
                        .append(ADD)
                        .append(tokenData.getLength());
                    break;
                case LEFT:
                    result.append(POINTER)
                        .append(SUBTRACT)
                        .append(tokenData.getLength());
                    break;
                case OUT:
                    result.append(PRINT_CHAR)
                        .append(CELL)
                        .append(CLOSING_BRACKET);
                    break;
                case IN:
                    result.append(CELL)
                        .append(READ_CHAR);
                    break;
                case START:
                    result.append(WHILE)
                        .append(CELL)
                        .append(CLOSING_BRACKET)
                        .append(OPENING_CURVY_BRACKET);
                    spaces += TAB;
                    break;
                case END:
                    result.delete(result.length() - TAB, result.length())
                        .append(CLOSING_CURVY_BRACKET);
                    spaces -= TAB;
                    break;
            }

            result.append(END_LINE);
        }

        return result.toString();
    }

    @Override
    public String fromHighLevel(String highLevelCode) {
        return "Not implemented yet";
    }
}
