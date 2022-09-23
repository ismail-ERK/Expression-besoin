package com.expressionbesoins.restexpbesoin.exception;

public class UserHasNotAuthorisation extends RuntimeException{
    public UserHasNotAuthorisation(String message) {
        super(message);
    }

    public UserHasNotAuthorisation() {
    }
}
