package neuralNetworks.algorithmics;

import neuralNetworks.objects.complexObjects.Layer;
import neuralNetworks.objects.complexObjects.WeightsMat;

import java.util.List;

public interface TrainingAlgorithm {
    public WeightsMat getCorrectedWeights(Layer inputLayer, Layer resultedLayer, Layer expectedLayer, WeightsMat correspondingWeights);
}
