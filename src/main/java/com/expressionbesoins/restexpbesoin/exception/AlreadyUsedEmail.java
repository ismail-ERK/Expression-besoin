package com.expressionbesoins.restexpbesoin.exception;

/**
 * @autor abdelhadi mouzafir
 */

// ! why Exception didn't work ?
public class AlreadyUsedEmail extends RuntimeException {


    private static final long serialVersionUID = 5861310537366287163L;

    public AlreadyUsedEmail() {
        super();
    }

    public AlreadyUsedEmail(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AlreadyUsedEmail(final String message) {
        super(message);
    }

    public AlreadyUsedEmail(final Throwable cause) {
        super(cause);
    }

}
