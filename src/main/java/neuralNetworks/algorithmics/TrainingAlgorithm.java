package neuralNetworks.algorithmics;

import neuralNetworks.objects.complexObjects.Layer;

public interface TrainingAlgorithm {
    public Layer getCurrectLayer(Layer resultedLayer, Layer expectedLayer);
}
