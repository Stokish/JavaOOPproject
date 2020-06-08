package Barista;

//Interface for Builder
public interface CoffeeMaker {
    void setNameOfCoffee(String nameOfCoffee);
    void setMilk(int milk);
    void setGramsOfCoffee(int gramsOfCoffee);
    void setCold(boolean cold);
    void setPenka(boolean penka);
    void setSugar(int sugar);
    void setWater(int water);
}
