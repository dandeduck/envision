package neuralNetworks.algorithmics;

import dataTypes.DoubleVector;
import dataTypes.Matrix;
import neuralNetworks.objects.Layer;

import java.util.List;

public abstract class TrainingAlgorithm {
    public abstract List<Layer> calcDescent(List<Layer> layers, DoubleVector desiredOutput);

    public double calcCost(DoubleVector outputLayer, DoubleVector desiredOutput) {
        return Math.pow(desiredOutput.subtract(outputLayer).sum(),2);
    }
}
