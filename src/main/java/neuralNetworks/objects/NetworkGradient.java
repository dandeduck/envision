package neuralNetworks.objects;

import dataTypes.Matrix;
import dataTypes.MatrixVector;

public class NetworkGradient {

    private Matrix biasLayersDescent;
    private MatrixVector weightMatsDescent;

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

    public void add(NetworkGradient gradient) {
        biasLayersDescent = biasLayersDescent.add(gradient.getBiasLayersDescent());
        weightMatsDescent = weightMatsDescent.add(gradient.getWeightMatsDescent());
    }

    public NetworkGradient divide(double val) {
        Matrix biasLayersDescentProduct = biasLayersDescent.scale(1/val);
        MatrixVector weightMatsDescentProduct = weightMatsDescent.scale(1/val);

        return new NetworkGradient(biasLayersDescentProduct, weightMatsDescentProduct);
    }

    @Override
    public String toString() {
        return  biasLayersDescent.toString();
    }
}
