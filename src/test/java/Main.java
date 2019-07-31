import dataTypes.Data;
import neuralNetworks.constants.enums.ActivationFunctionTypes;
import neuralNetworks.constants.enums.TrainingAlgorithmTypes;
import neuralNetworks.objects.complexObjects.Network;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Data data = new Data(2,1);
        data.addInputs(1.0,0.0);
        data.addOutput(1.0);

        Data data1 = new Data(2,1);
        data1.addInputs(1.0,1.0);
        data1.addOutput(0.0);

        List<Data> dataList = new ArrayList<>();
        dataList.add(data1);
        dataList.add(data);

        Network net = new Network(dataList, 100, ActivationFunctionTypes.SIGMOID, TrainingAlgorithmTypes.BACK_PROPAGATION, 0.25, 0.01, 2, 3, 1);
        net.train();
        System.out.println(net.compute(data1));
        System.out.println(net.compute(data));
    }
}
