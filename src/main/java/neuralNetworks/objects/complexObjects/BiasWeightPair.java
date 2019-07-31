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

    public Bias getBias() {
        return bias;
    }

    public void setWeights(WeightVector newWeights) {
        if(!weights.equals(this)) {
            weights.clear();
            weights.addAll(newWeights);
        }
    }

    @Override
    public String toString() {
        return getAdditionsToNextLayer().toString();
    }
}
