package Barista;

import Coffee.Coffee;
//Barista is a Builder from Builder Design Pattern
public class Barista implements CoffeeMaker {
    private String nameOfCoffee;
    private int sugar;
    private boolean penka;
    private boolean cold;
    private int gramsOfCoffee;
    private int milk;
    private int water;

    @Override
    public void setNameOfCoffee(String nameOfCoffee) {
        this.nameOfCoffee = nameOfCoffee;
    }

    @Override
    public void setWater(int water) {
        this.water = water;
    }

    @Override
    public void setSugar(int sugar) {
        this.sugar = sugar;
    }

    @Override
    public void setPenka(boolean penka) {
        this.penka = penka;
    }

    @Override
    public void setCold(boolean cold) {
        this.cold = cold;
    }

    @Override
    public void setGramsOfCoffee(int gramsOfCoffee) {
        this.gramsOfCoffee = gramsOfCoffee;
    }

    @Override
    public void setMilk(int milk) {
        this.milk = milk;
    }

    public Coffee getResult(){
        return  new Coffee(this.nameOfCoffee, this.sugar, this.penka, this.cold, this.gramsOfCoffee, this.milk, this.water);
    }
}
