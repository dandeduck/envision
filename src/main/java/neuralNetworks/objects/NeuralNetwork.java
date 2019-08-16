package neuralNetworks.objects;

import neuralNetworks.algorithmics.TrainingAlgorithm;
import neuralNetworks.constants.enums.ActivationType;
import neuralNetworks.constants.enums.TrainingType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NeuralNetwork {

    private Placeholder placeholder;
    private List<MiniBatch> miniBatches;

    public NeuralNetwork(ActivationType activationType, List<Integer> layerSizes) {
        placeholder = new Placeholder(activationType, layerSizes);
    }

    public NeuralNetwork(ActivationType activationType, Integer... layerSizes) {
        this(activationType, Arrays.asList(layerSizes));
    }

    public void train(TrainingType trainingType, List<NetworkPattern> trainingData, int batchSize) {
        do {
            miniBatches = divideDataIntoMiniBatches(trainingData, batchSize);

        } while(true);
    }

    private List<MiniBatch> divideDataIntoMiniBatches(List<NetworkPattern> allPatterns, int batchSize) {
        Collections.shuffle(allPatterns);
        return IntStream.range(0, batchSize)
                .mapToObj(batchIndex -> new MiniBatch(IntStream.range(batchIndex*batchSize, batchIndex*batchSize+batchSize)
                        .mapToObj(allPatterns::get)
                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    private Placeholder getBatchDescent(MiniBatch batch, TrainingAlgorithm trainingAlgorithm) {
        List<Placeholder> descents = batch.stream()
                .map(networkPattern -> trainingAlgorithm.calcDescent(placeholder.feedForward(networkPattern.getInput()), networkPattern.getOutput()))
                .collect(Collectors.toList());
        return calcAverageDescent(descents);
    }

    private Placeholder calcAverageDescent(List<Placeholder> batchDescents) {
        Placeholder sum = sumPlaceholder(batchDescents);

        return sum.scale(1/(batchDescents.size()+1));
    }

    private Placeholder sumPlaceholder(List<Placeholder> batchDescents) {
        Placeholder sum = batchDescents.remove(0);
        for(Placeholder placeholder : batchDescents)
            sum = sum.add(placeholder);
        return sum;
    }
}
