package neuralNetworks.constants.enums;
;
import neuralNetworks.algorithmics.TrainingAlgorithm;

import java.util.HashMap;
import java.util.Map;

public enum TrainingAlgorithmTypes {
    BACK_PROPAGATION;

    public TrainingAlgorithm getAlgorithm(double learningRate) {
        Map<TrainingAlgorithmTypes, TrainingAlgorithm> algorithms = new HashMap();
        algorithms.put(BACK_PROPAGATION, new BackPropagation(learningRate));

        return algorithms.get(this);
    }
}
