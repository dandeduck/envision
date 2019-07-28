package neuralNetworks.objects.complexObjects;

import dataTypes.Matrix;
import dataTypes.Vector;
import neuralNetworks.objects.basicObjects.Weight;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WeightsMat extends Matrix<Weight> {
    public WeightsMat(Collection collection) {
        super(collection);
    }

    public WeightsMat(int layerSize){
        super(null);
        addAll(initWeightMatrix(layerSize+1));
    }

    private Matrix<Weight> initWeightMatrix(int layerSize) {
        return new Matrix<>(IntStream.range(0, layerSize-1)
                .mapToObj(v -> initWeightVector(layerSize))
                .collect(Collectors.toList()));
    }

    private Vector<Weight> initWeightVector(int layerSize) {
        return new Vector<>(IntStream.range(0, layerSize-1)
                .mapToObj(w -> new Weight())
                .collect(Collectors.toList()));
    }
}
