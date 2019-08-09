package neuralNetworks.objects;

import dataTypes.Matrix;
import dataTypes.MatrixVector;

public class NetworkGradient {

    private final Matrix biasLayersDescent;
    private final MatrixVector weightMatsDescent;

    public NetworkGradient(Matrix biasLayersDescent, MatrixVector weightMatsDescent) {
        this.biasLayersDescent = biasLayersDescent;
        this.weightMatsDescent = weightMatsDescent;
    }

    public Matrix getBiasLayersDescent() {
        return biasLayersDescent;
    }

    public MatrixVector getWeightMatsDescent() {
        return weightMatsDescent;
    }

    public NetworkGradient add(NetworkGradient gradient) {
        Matrix biasLayersDescentSum = biasLayersDescent.add(gradient.getBiasLayersDescent());
        MatrixVector weightMatsDescentSum = weightMatsDescent.add(gradient.getWeightMatsDescent());

        return new NetworkGradient(biasLayersDescentSum, weightMatsDescentSum);
    }

    public NetworkGradient divide(double val) {
        Matrix biasLayersDescentProduct = biasLayersDescent.scale(1/val);
        MatrixVector weightMatsDescentProduct = weightMatsDescent.scale(1/val);

        return new NetworkGradient(biasLayersDescentProduct, weightMatsDescentProduct);
    }
}
