package neuralNetworks.objects.basicObjects;

import dataTypes.NumberValue;
import dataTypes.Value;

import java.util.Random;

public class Weight extends NumberValue {
    public Weight() {
        super(new Random().nextDouble() - new Random().nextDouble());
    }
    public Weight(Value<? extends Double> val) {
        super(val);
    }
    public Weight(double val) {
        super(val);
    }
}
