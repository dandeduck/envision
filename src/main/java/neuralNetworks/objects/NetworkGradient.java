package neuralNetworks.objects;

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
        Matrix neuronLayersDescentSum = neuronLayersDescent.add(gradient.getNeuronLayersDescent());
        Matrix biasLayersDescentSum = biasLayersDescent.add(gradient.getBiasLayersDescent());
        MatrixVector weightMatsDescentSum = weightMatsDescent.add(gradient.getWeightMatsDescent());

        return new NetworkGradient(neuronLayersDescentSum, biasLayersDescentSum, weightMatsDescentSum);
    }

    public NetworkGradient divide(double val) {
        Matrix neuronLayersDescentProduct = neuronLayersDescent.scale(val);
        Matrix biasLayersDescentProduct = biasLayersDescent.scale(val);
        MatrixVector weightMatsDescentProduct = weightMatsDescent.scale(val);

        return new NetworkGradient(neuronLayersDescentProduct, biasLayersDescentProduct, weightMatsDescentProduct);
    }
}
