package neuralNetworks.objects.complexObjects;

import dataTypes.Matrix;
import dataTypes.Value;
import dataTypes.Vector;
import neuralNetworks.objects.basicObjects.Neuron;
import neuralNetworks.objects.basicObjects.Weight;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WeightsMat extends Matrix<Weight> {
    public WeightsMat(Collection collection) {
        super(collection);
    }

    public WeightsMat(int layerSize, int nextLayerSize){
        super(new ArrayList());
        addAll(initWeightMatrix(layerSize, nextLayerSize));
    }

    private WeightsMat initWeightMatrix(int layerSize, int nextLayerSize) {
        return new WeightsMat(IntStream.range(0, nextLayerSize)
                .mapToObj(v -> initWeightVector(layerSize))
                .collect(Collectors.toList()));
    }

    private Vector<Weight> initWeightVector(int nextLayerSize) {
        return IntStream.range(0, nextLayerSize)
                .mapToObj(w -> new Weight())
                .collect(Collectors.toCollection(Vector::new));
    }

    public Vector<Weight> mulByNeurons(Vector<Neuron> neurons) {
        return mulByVector(toWeight(neurons));
    }

    private Vector<Weight> toWeight(Vector<Neuron> neurons) {
        return neurons.stream()
                .map(Weight::new)
                .collect(Collectors.toCollection(Vector::new));
    }
}
