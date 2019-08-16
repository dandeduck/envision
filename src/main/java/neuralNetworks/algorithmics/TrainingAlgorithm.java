package neuralNetworks.algorithmics;

import dataTypes.DoubleVector;
import neuralNetworks.objects.Placeholder;

public abstract class TrainingAlgorithm {
    public abstract Placeholder calcDescent(Placeholder placeholder, DoubleVector desiredOutput);

    public double calcCost(DoubleVector outputLayer, DoubleVector desiredOutput) {
        return Math.pow(desiredOutput.subtract(outputLayer).sum(),2);
    }
}
