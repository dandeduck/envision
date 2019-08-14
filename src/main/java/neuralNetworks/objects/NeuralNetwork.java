package neuralNetworks.objects;

import dataTypes.DoubleVector;
import neuralNetworks.algorithmics.ActivationFunction;
import neuralNetworks.algorithmics.TrainingAlgorithm;
import neuralNetworks.constants.enums.ActivationType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NeuralNetwork {

    private final ActivationFunction activationFunction;
    private List<Layer> layers;
    private List<MiniBatch> miniBatches;

    public NeuralNetwork(ActivationType activationType, List<Integer> layerSizes) {
        activationFunction = new ActivationFunction(activationType);
        layers = initLayers(layerSizes);
    }

    public NeuralNetwork(ActivationType activationType, Integer... layerSizes) {
        this(activationType, Arrays.asList(layerSizes));
    }

    private List<Layer> initLayers(List<Integer> layerSizes) {
        return IntStream.range(0, layerSizes.size())
                .skip(1)
                .mapToObj(index -> new Layer(layerSizes.get(index-1), layerSizes.get(index)))
                .collect(Collectors.toList());
    }

    private List<MiniBatch> divideDataIntoMiniBatches(List<NetworkPattern> allPatterns, int batchSize) {
        return IntStream.range(0, batchSize)
                .mapToObj(batchIndex -> new MiniBatch(IntStream.range(batchIndex*batchSize, batchIndex*batchSize+batchSize)
                        .mapToObj(allPatterns::get)
                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    private List<Layer> feedForward(DoubleVector input) {
        return IntStream.range(0, layers.size())
                .mapToObj(index -> index != 0 ?
                        layers.get(index).activate(layers.get(index-1).getNeurons(), activationFunction) :
                        layers.get(index).activate(input, activationFunction))
                .collect(Collectors.toList());
    }
}
