package neuralNetworks.objects;

public class NoCorrespondingWeightsException extends Exception {
    public static final String EXCEPTION_MSG = "This layer has no corresponding weights";

    public NoCorrespondingWeightsException(String msg) {
        super(msg);
    }
}
