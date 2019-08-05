package neuralNetworks.objects;

import dataTypes.DoubleVector;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NetworkPatternsCluster {

    private final List<NetworkPattern> clusterPatterns;

    public NetworkPatternsCluster(List<NetworkPattern> networkPatterns, int clusterSize) {
        clusterPatterns = pullCluster(networkPatterns, clusterSize);
    }

    private List<NetworkPattern> pullCluster(List<NetworkPattern> networkPatterns, int clusterSize) {
        return IntStream.range(0, clusterSize)
                .mapToObj(networkPatterns::remove)
                .collect(Collectors.toList());
    }

    public List<NetworkPattern> getPatterns() {
        return clusterPatterns;
    }
}
