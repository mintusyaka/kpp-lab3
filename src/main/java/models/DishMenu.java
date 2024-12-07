package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.stream;

public class DishMenu implements Serializable {
    private List<Dish> dishes;

    public DishMenu() {
        dishes = new ArrayList<Dish>();
    }

    public Dish getDish(String name) {
        Optional<Dish> dishResult =  dishes.stream()
                .filter(dish -> name.equals(dish.getTitle()))
                .findFirst();

        return dishResult.orElse(null);
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

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
