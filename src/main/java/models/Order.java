package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Order implements Serializable {
    private int id;
    private Client client;
    private int tableId;
    private transient List<Dish> dishes;

    public Order() {
    }

    public Order(int id, Client client, int tableId) {
        this.id = id;
        this.client = client;
        this.tableId = tableId;
        this.dishes = new ArrayList<Dish>();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    public int getTableId() {
        return tableId;
    }
    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public List<Dish> getDishes() {
        return dishes;
    }
    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
    public void addDish(Dish dish) {
        Optional<Dish> dishResult =  dishes.stream()
                .filter(existingDish -> dish.getTitle().equals(existingDish.getTitle()))
                .findFirst();

        if(!dishResult.isPresent()) {
            dishes.add(dish);
        }
        else {
            throw new RuntimeException("Duplicate dish title");
        }
    }

    @Override
    public String toString() {
        if(dishes == null || dishes.isEmpty()) {
            return "|id:" + id + "\n|client:\n" + client + "|tableId:" + tableId + '\n';
        }
        else if( client == null ) {
            String res = "|id:" + id + "\n|client:\n unknown \n|tableId:" + tableId + "|dishes:\n";
            int i = 0;
            for(Dish dish : dishes) {
                res += i++ + ". " + dish;
            }
            return res;
        }
        else {
            String res = "|id:" + id + "\n|client:\n" + client + "|tableId:" + tableId + "|dishes:\n";
            int i = 0;
            for(Dish dish : dishes) {
                res += i++ + ". " + dish;
            }
            return res;
        }
    }
}
