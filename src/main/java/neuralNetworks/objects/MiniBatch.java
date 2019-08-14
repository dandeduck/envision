package neuralNetworks.objects;

import dataTypes.DoubleVector;

import java.util.List;
import java.util.stream.Stream;

public class MiniBatch {

    private final List<NetworkPattern> networkPatterns;

    public MiniBatch(List<NetworkPattern> networkPatterns) {
        this.networkPatterns = networkPatterns;
    }

    public Stream<NetworkPattern> stream() {
        return networkPatterns.stream();
    }

    public NetworkPattern get(int index) {
        return networkPatterns.get(index);
    }

    public DoubleVector getInput(int index) {
        return get(index).getInput();
    }

    public DoubleVector getOutput(int index) {
        return get(index).getOutput() ;
    }
}
