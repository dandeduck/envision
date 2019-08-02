package neuralNetworks.objects.basicObjects;

import dataTypes.Value;

import java.util.Random;

public class Bias extends Value {
    public Bias() {
        super(new Random().nextDouble() - new Random().nextDouble());
    }

    public Bias(Value val) {
        super(val);
    }
}
