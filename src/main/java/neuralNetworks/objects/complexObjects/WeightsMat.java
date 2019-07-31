package neuralNetworks.objects.complexObjects;

import dataTypes.Matrix;
import neuralNetworks.objects.basicObjects.Weight;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WeightsMat extends Matrix<Weight> {
    public WeightsMat(Collection collection) {
        super(collection);
    }

    public WeightsMat() {
        super(new ArrayList());

    }

    public WeightsMat(int layerSize, int nextLayerSize){
        super(new ArrayList());
        addAll(initWeightMatrix(layerSize, nextLayerSize));
    }

    public WeightsMat(WeightVector vector) {
        super(new ArrayList());
        this.add(vector);
    }

    private WeightsMat initWeightMatrix(int layerSize, int nextLayerSize) {
        return IntStream.range(0, nextLayerSize)
                .mapToObj(v -> new WeightVector(layerSize))
                .collect(Collectors.toCollection(WeightsMat::new));
    }

    public WeightVector mulByNeurons(Layer neurons) {
        return new WeightVector(mulByVector(toWeight(neurons)));
    }

    private WeightVector toWeight(Layer neurons) {
        return neurons.stream()
                .map(Weight::new)
                .collect(Collectors.toCollection(WeightVector::new));
    }

    @Override
    public WeightVector get(int index) {
        return new WeightVector(super.get(index));
    }
}
