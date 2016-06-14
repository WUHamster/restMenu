package menu;

import javax.persistence.*;

/**
 * Created by WUHamster on 13.06.2016.
 */

@Entity
@Table(name = "Dishes")
public class Dishes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @Column
    private String name;
    @Column
    private int price;
    @Column
    private int weight;
    @Column
    private boolean discount;

    public Dishes() {
    }

    public Dishes(String name, int price, int weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        discount = false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isDiscount() {
        return discount;
    }

    public String toString(){
        String result = "Dish " + name + " with id: " + id + " costs: " + price + ", and weights " + weight;
        if (discount) result += " today with a discount!";
        return result;
    }
}
