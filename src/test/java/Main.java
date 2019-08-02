import neuralNetworks.constants.enums.ActivationFunctionTypes;
import neuralNetworks.constants.enums.TrainingAlgorithmTypes;
import neuralNetworks.objects.complexObjects.Network;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Data data = new Data(2,1);
        data.addInputs(1.0,0.0);
        data.addOutput(0.0);

        Data data1 = new Data(2,1);
        data1.addInputs(1.0,1.0);
        data1.addOutput(1.0);

        Data data2 = new Data(2,1);
        data2.addInputs(0.0,1.0);
        data2.addOutput(0.0);

        Data data3 = new Data(2,1);
        data3.addInputs(0.0,0.0);
        data3.addOutput(1.0);

        List<Data> dataList = new ArrayList<>();
        dataList.add(data);
        dataList.add(data1);
        dataList.add(data2);
        dataList.add(data3);

        Network net = new Network(dataList, 2, ActivationFunctionTypes.SIGMOID, TrainingAlgorithmTypes.BACK_PROPAGATION, 0.5, 2, 4, 1);
        net.train();
        System.out.println(net.compute(data1));
        System.out.println(net.compute(data));
    }
}
