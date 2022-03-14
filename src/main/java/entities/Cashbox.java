package entities;

import exceptions.NotEnoughMoneyException;

public class Cashbox {

    private volatile long money;

    public Cashbox(long money) {
        this.money = money;
    }

    synchronized public long getMoney() {
        return money;
    }

    synchronized public void setMoney(long money) {
        this.money = money;
    }

    synchronized public void putMoney(long money) {
        this.money += money;
    }

    synchronized public void withdrawMoney(long howMuch) throws NotEnoughMoneyException {
        if (money - howMuch < 0)
            throw new NotEnoughMoneyException();
        else
            this.money -= howMuch;
    }

}
