package neuralNetworks.algorithmics;

import neuralNetworks.constants.enums.ActivationFunctionTypes;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ActivationFunction {

    private final ActivationFunctionTypes funcType;
    private final Map<ActivationFunctionTypes, Consumer<Double>> functions;

    private double processedValue;

    public ActivationFunction(ActivationFunctionTypes funcType) {
        this.funcType = funcType;

        functions = new HashMap<>();
        functions.put(ActivationFunctionTypes.SIGMOID, v -> useSigmoid(v));
        functions.put(ActivationFunctionTypes.TANH, v -> useTanH(v));
        functions.put(ActivationFunctionTypes.RELU, v -> useReLU(v));
        functions.put(ActivationFunctionTypes.LINEAR, v -> useLinear(v));
        functions.put(ActivationFunctionTypes.LEAKY_RELU, v -> useLeakyReLU(v));
        functions.put(ActivationFunctionTypes.SOFTPLUS, v -> useSoftplus(v));
    }

    public double process(double preProcessValue) {
        functions.get(funcType).accept(preProcessValue);
        return processedValue;
    }

    private void useSigmoid(double preProcessedValue) {
        processedValue = 1/(1 + Math.exp(-preProcessedValue));
    }

    private void useTanH(double preProcessedValue) {
        processedValue = Math.tanh(preProcessedValue);
    }

    private void useReLU(double preProcessedValue) {
        processedValue = Math.max(0, preProcessedValue);
    }

    private void useLinear(double preProcessedValue) {
        processedValue = preProcessedValue;
    }

    private void useLeakyReLU(double preProcessedValue) {
        processedValue = preProcessedValue > 0 ? preProcessedValue : 0.01 * preProcessedValue;
    }

    private void useSoftplus(double preProcessedValue) {
        processedValue = Math.log(1 + Math.exp(preProcessedValue));
    }
}
