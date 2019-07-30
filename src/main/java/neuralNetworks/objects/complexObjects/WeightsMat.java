package neuralNetworks.objects.complexObjects;

import dataTypes.Matrix;
import dataTypes.Vector;
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
        addAll(initWeightMatrix(layerSize+1, nextLayerSize+1));
    }

    private WeightsMat initWeightMatrix(int layerSize, int nextLayerSize) {
        return new WeightsMat(IntStream.range(0, layerSize-1)
                .mapToObj(v -> initWeightVector(nextLayerSize))
                .collect(Collectors.toList()));
    }

    private Vector<Weight> initWeightVector(int nextLayerSize) {
        return new Vector<>(IntStream.range(0, nextLayerSize-1)
                .mapToObj(w -> new Weight())
                .collect(Collectors.toList()));
    }
}
