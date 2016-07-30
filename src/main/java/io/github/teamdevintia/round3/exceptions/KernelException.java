package io.github.teamdevintia.round3.exceptions;

/**
 * @author Shad0wCore
 */
public class KernelException extends Exception {

    public KernelException() {
        super();
    }

    public KernelException(String message) {
        super(message);
    }

    public KernelException(String message, Throwable cause) {
        super(message, cause);
    }

    public KernelException(Throwable cause) {
        super(cause);
    }

    protected KernelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
