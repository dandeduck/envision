package neuralNetworks.algorithmics;

import dataTypes.Data;
import neuralNetworks.objects.complexObjects.Layer;
import neuralNetworks.objects.complexObjects.WeightsMat;

import java.util.List;

public interface TrainingAlgorithm {
    double getOutput(List<Layer> layers, List<WeightsMat> weightMats, Layer wantedOutput);
    boolean hasLearned(Layer outputLayer, Data outputPattern);
}
