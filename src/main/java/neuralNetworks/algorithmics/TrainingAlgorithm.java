package neuralNetworks.algorithmics;

import dataTypes.DoubleVector;
import dataTypes.Matrix;
import dataTypes.MatrixVector;
import neuralNetworks.objects.NetworkGradient;

public abstract class TrainingAlgorithm {
    public abstract NetworkGradient calcGradientDescentStep(Matrix neuronLayers, Matrix biasLayers, MatrixVector weightMats, DoubleVector outputPattern);

    public double calcCost(DoubleVector outputLayer, DoubleVector desiredOutput) {
        return Math.pow(new DoubleVector(desiredOutput.subtract(outputLayer)).sum(),2);
    }
}
