package neuralNetworks.algorithmics;

import neuralNetworks.objects.Layer;

public interface TrainingAlgorithm {
    public Layer getCurrectLayer(Layer resultedLayer, Layer expectedLayer);
}
