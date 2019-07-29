package neuralNetworks.objects.complexObjects;


import dataTypes.Matrix;
import dataTypes.Vector;
import neuralNetworks.algorithmics.ActivationFunction;
import neuralNetworks.constants.enums.ActivationFunctionTypes;
import neuralNetworks.objects.exception.NoCorrespondingWeightsException;
import neuralNetworks.objects.basicObjects.Bias;
import neuralNetworks.objects.basicObjects.Neuron;
import neuralNetworks.objects.basicObjects.Weight;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Network {

    private final List<Layer> layers;
    private final List<WeightsMat> weightMatrices;

    private final ActivationFunction activationFunction;

    public Network(ActivationFunctionTypes functionType, Integer... layerSizes) {
        activationFunction = new ActivationFunction(functionType);

        layers = initLayers(Arrays.asList(layerSizes));
        weightMatrices = initWeightMatrices(Arrays.asList(layerSizes));
    }

    private List<Layer> initLayers(List<Integer> layerSizes) {
        return layerSizes.stream()
                .map(l -> layerSizes.indexOf(l) == 0 ? new Layer(l,false) : new Layer(l))
                .collect(Collectors.toList());
    }

    private List<WeightsMat> initWeightMatrices(List<Integer> layerSizes) {
        return IntStream.range(1, layerSizes.size())
                .skip(1)
                .mapToObj(m -> new WeightsMat(getPrevInt(layerSizes, m), layerSizes.get(m)))
                .collect(Collectors.toList());
    }

    private int getPrevInt(List<Integer> list, int curr) {
        return list.get(list.indexOf(curr)-1);
    }

    private void feedFarward(Vector<Neuron> input) {
        updateInputNeurons(input);
        layers.stream()
                .skip(1)
                .forEach(l -> feedNextLayer(getPrevLayer(layers,l),l));
    }

    private void updateInputNeurons(Vector<Neuron> input) {
        layers.get(0).updateLayer(input);
    }

    private Layer getPrevLayer(List<Layer> layers, Layer currentLayer) {
        return layers.get(layers.indexOf(currentLayer)-1);
    }


    private void feedNextLayer(Layer prevLayer, Layer nextLayer) {
        Matrix WeightsMat = getCorrespondingWeights(nextLayer);
        Vector<Neuron> prevValues = prevLayer.getValues();

        nextLayer.updateLayer(calcNextValues(WeightsMat, prevValues));
    }

    private Matrix<Weight> getCorrespondingWeights(Layer layer){
        try {
            checkIfLayerHasCorrespondingWeights(layer);
        } catch (NoCorrespondingWeightsException e) {
            e.printStackTrace();
        }
        return weightMatrices.get(layers.indexOf(layer)-1);
    }

    private void checkIfLayerHasCorrespondingWeights(Layer layer) throws NoCorrespondingWeightsException {
        if(layers.indexOf(layer) == 0)
            throw new NoCorrespondingWeightsException();
    }

    private Vector<Neuron> calcNextValues(Matrix<Weight> W, Vector<Neuron> a) {
        return new Vector<>(W.mulByVector(toWeights(a)).stream()
                .map(v -> activationFunction.process(v))
                .collect(Collectors.toList()));
    }

    private Vector<Weight> toWeights(Vector<Neuron> neurons) {
        return new Vector<>(neurons.stream()
                .map(n -> new Weight(n))
                .collect(Collectors.toList()));
    }
}
