import dataTypes.Data;
import neuralNetworks.constants.enums.ActivationFunctionTypes;
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
        dataList.add(data);
        dataList.add(data1);

        System.out.println(dataList);

        Network net = new Network(dataList, ActivationFunctionTypes.SIGMOID, 0.25, 0.01, 2, 3, 1);
        net.train();
    }
}
