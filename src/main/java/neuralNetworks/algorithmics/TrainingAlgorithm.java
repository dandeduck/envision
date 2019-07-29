package neuralNetworks.algorithmics;

import dataTypes.Data;
import neuralNetworks.objects.complexObjects.Layer;
import neuralNetworks.objects.complexObjects.WeightsMat;

import java.util.List;

public interface TrainingAlgorithm {
    List<WeightsMat> computeOutputPattern(List<Layer> layers, List<WeightsMat> weightMats, Data outputPattern);
    boolean hasLearned(Layer outputLayer, Data outputPattern);
}
