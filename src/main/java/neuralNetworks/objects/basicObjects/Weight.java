package neuralNetworks.objects.basicObjects;

import dataTypes.Value;

import java.util.Random;

public class Weight extends Value {
    public Weight() {
        super(new Random().nextDouble() - new Random().nextDouble());
    }

    public Weight(Value val) {
        super(val);
    }
}
