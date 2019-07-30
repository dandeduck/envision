package neuralNetworks.objects.complexObjects;

import dataTypes.Vector;
import neuralNetworks.objects.basicObjects.Bias;
import neuralNetworks.objects.basicObjects.Weight;

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



    public WeightVector getAdditionsToNextLayer() {
        return weights.stream()
                .map(n -> n.mul(bias.get()))
                .collect(Collectors.toCollection(WeightVector::new));
    }

    private void setBias(Bias newBias) {
        bias.set(newBias.get());
    }
}
