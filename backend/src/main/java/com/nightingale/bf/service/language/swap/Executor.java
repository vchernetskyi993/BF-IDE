package com.nightingale.bf.service.language.swap;

import com.nightingale.bf.model.ExecutionData;
import com.nightingale.bf.model.operation.OperationToken;
import com.nightingale.bf.model.operation.OperationType;
import com.nightingale.bf.service.execute.BitExecutor;
import com.nightingale.bf.service.operation.Operations;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service("swapExecutor")
public class Executor extends BitExecutor {
    private final Operations swapOperations;

    public Executor(Operations swapOperations) {
        this.swapOperations = swapOperations;
    }

    @Override
    protected Operations getOperations() {
        return swapOperations;
    }

    protected List<Integer> createTape(Collection<Integer> input) {
        return input.stream()
            .flatMap(this::intToBits)
            .collect(Collectors.collectingAndThen(
                Collectors.toCollection(LinkedList::new),
                list -> {
                    Collections.reverse(list);
                    return list;
                }));
    }

    private Stream<Integer> intToBits(int input) {
        return IntStream.range(0, BITS_NUM)
            .map(shift -> input >> shift)
            .map(n -> n & 1)
            .boxed();
    }

    @Override
    protected void delegateToChildren(OperationToken token,
                                      List<Integer> tape,
                                      ExecutionData data) {
        super.delegateToChildren(token, tape, data);
        if (token.getType() == OperationType.SWAP) {
            if (data.getSwapReg() == -1) {
                data.setSwapReg(data.getPointer());
            } else {
                var temp = tape.get(data.getSwapReg());
                tape.set(data.getSwapReg(), tape.get(data.getPointer()));
                tape.set(data.getPointer(), temp);
                data.setSwapReg(-1);
            }
        }
    }
}
