package neuralNetworks.objects.complexObjects;

import dataTypes.Matrix;
import dataTypes.Vector;
import neuralNetworks.objects.basicObjects.Bias;
import neuralNetworks.objects.basicObjects.Neuron;

import java.util.List;
import java.util.stream.Collectors;
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

    private void setLayerAdditions(List<? extends Vector> additions) {
        layerAdditions.clear();
        layerAdditions.stream()
                .map(Layer::new)
                .collect(Collectors.toList());
    }

    private void setBiasAdditions(Matrix<Bias> additions) {
        biasAdditions.clear();
        biasAdditions.addAll(additions);
    }

    private void setWeightAdditions(List<WeightsMat> additions) {
        weightAdditions.clear();
        weightAdditions.addAll(additions);
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

    public void averageAdditions(List<NetworkAdditions> additions) {
        additions.forEach();
    }

    private void sum(NetworkAdditions additions) {
        setLayerAdditions(sumLists(layerAdditions, additions.getLayerAdditions()));
        setBiasAdditions(biasAdditions.sum(additions.getBiasAdditions()));
        setWeightAdditions();
    }

    private List<Vector> sumLists(List<? extends Vector> a, List<? extends Vector> b) {
        return IntStream.range(0, a.size())
                .mapToObj(i -> a.get(i).sum(b.get(i)))
                .collect(Collectors.toList());
    }

    private
}
