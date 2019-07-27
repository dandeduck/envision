package dataTypes;

import java.util.ArrayDeque;
import java.util.Deque;

public class Data<T> {

    private static final String ILLEGAL_DATA_MSG = "There must be the same number of input and output data points";

    private Deque<T> input;
    private Deque<T> output;

    public Data(Deque<T> input, Deque<T> output) {
        this.input = input;
        this.output = output;

        checkIfDataValid(input,output);
    }

    public Data(int inputSize, int outputSize) {
        this(new ArrayDeque<>(inputSize),new ArrayDeque<>(outputSize));
    }

    public void dataPoint(T inputPoint, T outputPoint) {
        input.add(inputPoint);
        output.add(outputPoint);
    }

    private void checkIfDataValid(Deque input, Deque output) throws IllegalArgumentException {
        if(input.size() != output.size())
            throw new IllegalArgumentException(ILLEGAL_DATA_MSG);
    }
}
