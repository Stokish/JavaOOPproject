package Coffee;

//Out Coffee Product
public class Coffee {
    private String nameOfCoffee;
    private int sugar;
    //it is called penka just because
    private boolean penka;
    private boolean cold;
    private int gramsOfCoffee;
    private int milk;
    private int water;

    public Coffee(String nameOfCoffee, int sugar, boolean penka, boolean cold, int gramsOfCoffee, int milk, int water) {
        this.nameOfCoffee = nameOfCoffee;
        this.sugar = sugar;
        this.penka = penka;
        this.cold = cold;
        this.gramsOfCoffee = gramsOfCoffee;
        this.milk = milk;
        this.water = water;
    }

    public String getNameOfCoffee() {
        return nameOfCoffee;
    }

    public int getWater() {
        return water;
    }

    public int getSugar() {
        return sugar;
    }

    public boolean isPenka() {
        return penka;
    }

    public boolean isCold() {
        return cold;
    }

    public int getGramsOfCoffee() {
        return gramsOfCoffee;
    }

    public int getMilk() {
        return milk;
    }

    @Override
    public String toString() {
        String p = (isPenka())? ", has Penka": ", without Penka";
        String c = (isCold())? " is cold" : " is hot";
        return " This " + getNameOfCoffee() + c + " and contains " +
                "grams of sugar = " + sugar +
                p  +
                ", grams of coffee = " + gramsOfCoffee +
                ", milk = " + milk +
                "ml, water = " + water +
                "ml.";
    }
}

