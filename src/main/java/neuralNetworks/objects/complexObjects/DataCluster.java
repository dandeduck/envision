package neuralNetworks.objects.complexObjects;

import dataTypes.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DataCluster extends ArrayList<Data> {

    private final int clusterSize;

    public DataCluster(int clusterSize) {
        this.clusterSize = clusterSize;
    }

    public List<Data> addData(Collection<Data> data) {
        return data.stream()
                .limit(clusterSize)
                .map(d -> {add(d); return d;})
                .collect(Collectors.toList());
    }
}
