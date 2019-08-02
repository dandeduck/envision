package neuralNetworks.objects.basicObjects;

import dataTypes.NumberValue;
import dataTypes.Value;

import java.util.Random;

public class Bias extends NumberValue {
    public Bias() {
        super(new Random().nextDouble() - new Random().nextDouble());
    }
    public Bias(Value<? extends Double> val) {
        super(val);
    }
    public Bias(double val) {
        super(val);
    }
}
