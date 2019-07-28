package neuralNetworks.objects;

public class NoCorrespondingBiasesException extends Exception{
    public static final String EXCEPTION_MSG = "This layer has no corresponding biases";

    public NoCorrespondingBiasesException(String msg) {
        super(msg);
    }
}
