package neuralNetworks.objects.basicObjects;

import dataTypes.NumberValue;
import dataTypes.Value;

public class Neuron extends NumberValue {
    public Neuron() {
        super(0);
    }
    public Neuron(Value<? extends Double> val) {
        super(val);
    }
    public Neuron(double val) {
        super(val);
    }
}
