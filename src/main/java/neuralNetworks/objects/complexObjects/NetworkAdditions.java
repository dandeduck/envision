package neuralNetworks.objects.complexObjects;

import dataTypes.Matrix;
import neuralNetworks.objects.basicObjects.Bias;

import java.util.List;
import java.util.stream.IntStream;

public class NetworkAdditions {

    private final List<Layer> layerAdditions;
    private final Matrix<Bias> biasAdditions;
    private final List<WeightsMat> weightAdditions;

    public NetworkAdditions(List<Layer> layerAdditions, Matrix<Bias> biasAdditions, List<WeightsMat> weightAdditions) {
        this.layerAdditions = layerAdditions;
        this.biasAdditions = biasAdditions;
        this.weightAdditions = weightAdditions;
    }

    public List<Layer> getLayerAdditions() {
        return layerAdditions;
    }

    public Matrix<Bias> getBiasAdditions() {
        return biasAdditions;
    }

    public List<WeightsMat> getWeightAdditions() {
        return weightAdditions;
    }

    public void averageAdditions(NetworkAdditions additions) {

    }

    private void averageLayerAdditions(List<Layer> additions) {
        IntStream.range(0, layerAdditions.size())
                .forEach();
    }
}
