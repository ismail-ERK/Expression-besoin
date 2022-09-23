package com.expressionbesoins.restexpbesoin.exception;

public class ConfirmPassowrdIncorrect extends RuntimeException{
    public ConfirmPassowrdIncorrect() {
    }
    public ConfirmPassowrdIncorrect(String message) {
        super(message);
    }
}
