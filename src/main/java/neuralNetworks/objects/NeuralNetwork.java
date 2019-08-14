package neuralNetworks.objects;

import neuralNetworks.algorithmics.ActivationFunction;
import neuralNetworks.algorithmics.TrainingAlgorithm;
import neuralNetworks.constants.enums.ActivationType;

import java.util.Arrays;
import java.util.List;

public class NeuralNetwork {

    private final ActivationFunction activationFunction;
    private final List<Layer> layers;

    public NeuralNetwork(ActivationType activationType, List<Integer> layerSizes) {
        activationFunction = new ActivationFunction(activationType);
    }

    public NeuralNetwork(ActivationType activationType, Integer... layerSizes) {
        this(activationType, Arrays.asList(layerSizes));
    }
}
