package neuralNetworks.algorithmics;

import dataTypes.Data;
import neuralNetworks.objects.Layer;

import java.util.Deque;
import java.util.List;

public interface TrainingAlgorithm {
    public Layer getCurrectLayer(Layer resultedLayer, Layer expectedLayer);
}
