package neuralNetworks.algorithmics;

import neuralNetworks.constants.enums.ActivationFunctionTypes;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ActivationFunction {

    private final ActivationFunctionTypes funcType;
    private final Map<ActivationFunctionTypes, Function<Double,Double>> functions;

    private double processedValue;

    public ActivationFunction(ActivationFunctionTypes funcType) {
        this.funcType = funcType;

        functions = new HashMap<>();
        functions.put(ActivationFunctionTypes.SIGMOID, this::sigmoid);
        functions.put(ActivationFunctionTypes.TANH, this::tanH);
        functions.put(ActivationFunctionTypes.RELU, this::reLU);
        functions.put(ActivationFunctionTypes.LINEAR, this::linear);
        functions.put(ActivationFunctionTypes.LEAKY_RELU, this::leakyReLU);
        functions.put(ActivationFunctionTypes.SOFTPLUS, this::softplus);
    }

    public double process(double preProcessValue) {
        return functions.get(funcType).apply(preProcessValue);
    }

    private double sigmoid(double preProcessedValue) {
        return 1/(1 + Math.exp(-preProcessedValue));
    }

    private double tanH(double preProcessedValue) {
        return Math.tanh(preProcessedValue);
    }

    private double reLU(double preProcessedValue) {
        return Math.max(0, preProcessedValue);
    }

    private double linear(double preProcessedValue) {
        return preProcessedValue;
    }

    private double leakyReLU(double preProcessedValue) {
        return preProcessedValue > 0 ? preProcessedValue : 0.01 * preProcessedValue;
    }

    private double softplus(double preProcessedValue) {
        return Math.log(1 + Math.exp(preProcessedValue));
    }
}
