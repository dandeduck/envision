package neuralNetworks.algorithmics;

import dataTypes.DoubleVector;
import dataTypes.Matrix;

public abstract class TrainingAlgorithm {
    public abstract NetworkGradient calcGradientDescentStep(Matrix neuronLayers, MatrixVector weightMats, DoubleVector outputPattern);

    public double calcCost(DoubleVector outputLayer, DoubleVector desiredOutput) {
        return Math.pow(desiredOutput.subtract(outputLayer).sum(),2);
    }
}
