import dataTypes.Data;
import neuralNetworks.constants.enums.ActivationFunctionTypes;
import neuralNetworks.objects.complexObjects.Network;

public class Main {
    public static void main(String[] args) {
        Data data = new Data(2,1);
        data.addInputs(1.0,0.0);
        data.addOutput(1.0);

        Network net = new Network(data, ActivationFunctionTypes.SIGMOID, 0.25, 2, 3, 1);
        net.train();
    }
}
