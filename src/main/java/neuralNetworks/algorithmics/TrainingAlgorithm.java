package neuralNetworks.algorithmics;

import dataTypes.Data;
import neuralNetworks.objects.complexObjects.BiasWeightPair;
import neuralNetworks.objects.complexObjects.Layer;
import neuralNetworks.objects.complexObjects.WeightVector;
import neuralNetworks.objects.complexObjects.WeightsMat;

import java.util.List;

public interface TrainingAlgorithm {
    List<WeightsMat> getAdjustedWeights(List<Layer> layers, List<WeightsMat> weightMats, Data outputPattern);
    List<WeightVector> getAdjustedBiasWeightList(List<BiasWeightPair> biasWeightPair, List<Layer> layers, Data outputPattern);
    boolean hasLearned(Layer outputLayer, Data outputPattern);
}
