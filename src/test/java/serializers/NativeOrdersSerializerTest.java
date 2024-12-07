package serializers;

import managers.ClientManager;
import managers.DishManager;
import models.Client;
import models.Dish;
import models.DishMenu;
import models.Order;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.ref.Cleaner;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class NativeOrdersSerializerTest {

    List<Order> orders;
    String filename;

    @BeforeEach
    void setUp() {
        orders = new ArrayList<>();
        filename = "orders_test.ser";

        Client c = new Client(0, "John", 1);
        orders.add(new Order(0, c, c.getTableId()));
        orders.get(orders.size() - 1).setDishes(new ArrayList<>());
        orders.get(orders.size() - 1).getDishes().add(new Dish("Dish1", "Description1", false));
        orders.get(orders.size() - 1).getDishes().add(new Dish("Dish2", "Description2", true));
        orders.get(orders.size() - 1).getDishes().add(new Dish("Dish3", "Description3", true));
        orders.get(orders.size() - 1).getDishes().add(new Dish("Dish4", "Description4", false));

        c = new Client(1, "Jane", 2);
        orders.add(new Order(1, c, c.getTableId()));
        orders.get(orders.size() - 1).setDishes(new ArrayList<>());
        orders.get(orders.size() - 1).getDishes().add(new Dish("Dish1", "Description1", false));
        orders.get(orders.size() - 1).getDishes().add(new Dish("Dish2", "Description2", true));

        c = new Client(2, "Tom", 3);
        orders.add(new Order(2, c, c.getTableId()));
        orders.get(orders.size() - 1).setDishes(new ArrayList<>());
        orders.get(orders.size() - 1).getDishes().add(new Dish("Dish1", "Description1", false));
        orders.get(orders.size() - 1).getDishes().add(new Dish("Dish2", "Description2", true));

        c = new Client(3, "Jerry", 4);
        orders.add(new Order(3, c, c.getTableId()));
        orders.get(orders.size() - 1).setDishes(new ArrayList<>());
        orders.get(orders.size() - 1).getDishes().add(new Dish("Dish1", "Description1", false));
        orders.get(orders.size() - 1).getDishes().add(new Dish("Dish2", "Description2", true));
        orders.get(orders.size() - 1).getDishes().add(new Dish("Dish3", "Description3", false));
        orders.get(orders.size() - 1).getDishes().add(new Dish("Dish4", "Description4", true));

        c = new Client(4, "Mike", 5);
        orders.add(new Order(4, c, c.getTableId()));
        c = new Client(5, "Jessica", 6);
        orders.add(new Order(5, c, c.getTableId()));
        c = new Client(6, "Jane", 7);
    }

    @org.junit.jupiter.api.Test
    void serialize() {
        NativeOrdersSerializer serializer = new NativeOrdersSerializer();

        try {
            serializer.serialize(orders, filename);
        } catch (Exception e) {
            fail("Bad serialization");
        }

        File file = new File(filename);
        if(!file.exists())
        {
            fail("File don't exist");
        }


    }

    @org.junit.jupiter.api.Test
    void deserialize() {
        NativeOrdersSerializer serializer = new NativeOrdersSerializer();

        List<Order> ordersAfter;
        try {
            ordersAfter = serializer.deserialize(filename);

            assertEquals(orders.size(), ordersAfter.size());

            for(Order order : ordersAfter)
            {
                assertNull(order.getDishes());
            }
        } catch (Exception e) {
            fail("Bad deserialization");
        }
    }
}