package neuralNetworks.algorithmics;

import neuralNetworks.constants.enums.ActivationType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ActivationFunction {

    private final ActivationType funcType;
    private final Map<ActivationType, Function<Double,Double>> functions;

    private double processedValue;

    public ActivationFunction(ActivationType funcType) {
        this.funcType = funcType;

        functions = new HashMap<>();
        functions.put(ActivationType.SIGMOID, this::sigmoid);
        functions.put(ActivationType.TANH, this::tanH);
        functions.put(ActivationType.RELU, this::reLU);
        functions.put(ActivationType.LINEAR, this::linear);
        functions.put(ActivationType.LEAKY_RELU, this::leakyReLU);
        functions.put(ActivationType.SOFTPLUS, this::softplus);
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
