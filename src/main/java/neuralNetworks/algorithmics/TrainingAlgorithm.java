package neuralNetworks.algorithmics;

import neuralNetworks.constants.enums.TrainingAlgorithmTypes;

import java.util.HashMap;

public class TrainingAlgorithm {

    private final TrainingAlgorithmTypes algorithmType;

    public TrainingAlgorithm(TrainingAlgorithmTypes algorithmType) {
        this.algorithmType = algorithmType;
    }
}
