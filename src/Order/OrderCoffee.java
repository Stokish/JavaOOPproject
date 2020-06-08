package Order;

import Barista.CoffeeMaker;

public class OrderCoffee {

    //OrderCoffee is a Director which uses builders
    public void MakeLatte(CoffeeMaker coffeeMaker){
        coffeeMaker.setNameOfCoffee("Latte");
        coffeeMaker.setCold(false);
        coffeeMaker.setGramsOfCoffee(10);
        coffeeMaker.setMilk(300);
        coffeeMaker.setWater(25);
        coffeeMaker.setPenka(true);
        coffeeMaker.setSugar(5);
    }

    public void MakeAmericano(CoffeeMaker coffeeMaker){
        coffeeMaker.setNameOfCoffee("Americano");
        coffeeMaker.setCold(false);
        coffeeMaker.setGramsOfCoffee(11);
        coffeeMaker.setMilk(50);
        coffeeMaker.setWater(120);
        coffeeMaker.setPenka(false);
        coffeeMaker.setSugar(0);
    }

    public void MakeCappuccino(CoffeeMaker coffeeMaker){
        coffeeMaker.setNameOfCoffee("Cappuccino");
        coffeeMaker.setCold(false);
        coffeeMaker.setGramsOfCoffee(15);
        coffeeMaker.setMilk(70);
        coffeeMaker.setWater(25);
        coffeeMaker.setPenka(true);
        coffeeMaker.setSugar(0);
    }
}
