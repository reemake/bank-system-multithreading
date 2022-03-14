package exceptions;

public class NotEnoughMoneyException extends Exception {

    public NotEnoughMoneyException() {
        super("Not enough money in the cashbox!");
    }
}
