package neuralNetworks.objects.complexObjects;

import dataTypes.Vector;
import neuralNetworks.objects.basicObjects.Weight;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WeightVector extends Vector<Weight> {
    public WeightVector() {
    }

    public WeightVector(Collection collection) {
        addAll(collection);
    }

    public WeightVector(int size) {
        this.addAll(initWeights(size));
    }

    private List<Weight> initWeights(int nextLayerSize) {
        return IntStream.range(0, nextLayerSize)
                .mapToObj(i -> new Weight())
                .collect(Collectors.toList());
    }

    @Override
    public Weight get(int index) {
        return new Weight(super.get(index));
    }
}
