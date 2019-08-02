package neuralNetworks.objects.complexObjects;

import dataTypes.Matrix;
import dataTypes.Vector;
import neuralNetworks.objects.basicObjects.Bias;
import neuralNetworks.objects.basicObjects.Neuron;
import neuralNetworks.objects.basicObjects.Weight;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NetworkDescent {

    private final Matrix<Neuron> layersDescent;
    private final Matrix<Bias> biasesDescent;
    private final Vector<Matrix<Weight>> weightsDescent;

    public NetworkDescent() {
        layersDescent = new Matrix<>();
        biasesDescent = new Matrix<>();
        weightsDescent = new Vector<>();
    }

    public NetworkDescent(Matrix<Neuron> layersDescent, Matrix<Bias> biasesDescent, Vector<Matrix<Weight>> weightsDescent) {
        this();
        this.layersDescent.set(layersDescent);
        this.biasesDescent.set(biasesDescent);
        this.weightsDescent.set(weightsDescent);
    }

    public Matrix<Neuron> getLayersDescent() {
        return layersDescent;
    }

    public Matrix<Bias> getBiasesDescent() {
        return biasesDescent;
    }

    public Vector<Matrix<Weight>> getWeightsDescent() {
        return weightsDescent;
    }

    public void averageAdditions(List<NetworkDescent> descents) {
        descents.forEach(this::sum);
        scale(1/descents.size());
    }

    private void sum(NetworkDescent additions) {
        layersDescent.sum(additions.getLayersDescent());
        biasesDescent.sum(additions.getBiasesDescent());
        weightsDescent.sub(additions.getWeightsDescent());
    }

    private void scale(double scaleVal) {
        layersDescent.scale(new Neuron(scaleVal));
        biasesDescent.scale(new Bias(scaleVal));
        weightsDescent.scale(new Weight(scaleVal));
    }
}
