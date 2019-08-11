package neuralNetworks.objects;

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
                .mapToObj(i -> networkPatterns.remove(0))
                .collect(Collectors.toList());
    }

    public List<NetworkPattern> getPatterns() {
        return clusterPatterns;
    }

    @Override
    public String toString() {
        return clusterPatterns.toString();
    }
}
