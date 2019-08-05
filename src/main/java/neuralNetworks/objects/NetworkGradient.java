package neuralNetworks.objects;

import dataTypes.DoubleVector;
import dataTypes.Matrix;
import dataTypes.MatrixVector;

public class NetworkGradient {

    private final Matrix neuronLayersDescent;
    private final Matrix biasLayersDescent;
    private final MatrixVector weightMatsDescent;

    public NetworkGradient(Matrix neuronLayersDescent, Matrix biasLayersDescent, MatrixVector weightMatsDescent) {
        this.neuronLayersDescent = neuronLayersDescent;
        this.biasLayersDescent = biasLayersDescent;
        this.weightMatsDescent = weightMatsDescent;
    }

    public Matrix getNeuronLayersDescent() {
        return neuronLayersDescent;
    }

    public Matrix getBiasLayersDescent() {
        return biasLayersDescent;
    }

    public MatrixVector getWeightMatsDescent() {
        return weightMatsDescent;
    }

    public NetworkGradient add(NetworkGradient gradient) {
        Matrix neuronLayersDescentSum = new Matrix(neuronLayersDescent.add(gradient.getNeuronLayersDescent()));
        Matrix biasLayersDescentSum = new Matrix(biasLayersDescent.add(gradient.getBiasLayersDescent()));
        MatrixVector weightMatsDescentSum = new MatrixVector(weightMatsDescent.add(gradient.getWeightMatsDescent()));

        return new NetworkGradient(neuronLayersDescentSum, biasLayersDescentSum, weightMatsDescentSum);
    }

    public NetworkGradient divide(double val) {
        Matrix neuronLayersDescentProduct = new Matrix(neuronLayersDescent.scale(new DoubleVector()));
        Matrix biasLayersDescentProduct = new Matrix(biasLayersDescent.multiply(gradient.getBiasLayersDescent()));
        MatrixVector weightMatsDescentProduct = new MatrixVector(weightMatsDescent.multiply(gradient.getWeightMatsDescent()));

        return new NetworkGradient(neuronLayersDescentProduct, biasLayersDescentProduct, weightMatsDescentProduct);
    }
}
