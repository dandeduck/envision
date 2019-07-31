package neuralNetworks.objects.complexObjects;

import neuralNetworks.objects.basicObjects.Bias;

import java.util.stream.Collectors;

public class BiasWeightPair {

    private Bias bias;
    private WeightVector weights;

    public BiasWeightPair(int nextLayerSize, Bias bias) {
        this.bias = bias;
        weights = new WeightVector(nextLayerSize);
    }

    public BiasWeightPair(int nextLayerSize) {
        this(nextLayerSize, new Bias());
    }

    public WeightVector getWeights() {
        return weights;
    }

    public WeightVector getAdditionsToNextLayer() {
        weights.forEach(n -> n.mul(bias.get()));
        return weights.stream()
                .map(n -> n.mul(bias.get()))
                .collect(Collectors.toCollection(WeightVector::new));
    }

    private void setBias(Bias newBias) {
        bias.set(newBias.get());
    }

    @Override
    public String toString() {
        return getAdditionsToNextLayer().toString();
    }
}
