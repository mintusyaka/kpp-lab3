package serializers;

import models.Client;
import models.Dish;
import models.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class YamlOrdersSerializerTest {

    List<Order> orders;
    String filename;

    @BeforeEach
    void setUp() {
        orders = new ArrayList<>();
        filename = "orders_test.yaml";

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

    @Test
    void serialize() {
        YamlOrdersSerializer serializer = new YamlOrdersSerializer();
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

    @Test
    void deserialize() {
        YamlOrdersSerializer serializer = new YamlOrdersSerializer();

        List<Order> ordersAfter;
        try {
            ordersAfter = serializer.deserialize(filename);

            assertEquals(orders.size(), ordersAfter.size());

            for(Order order : ordersAfter)
            {
                if(order.getDishes().size() > 3)
                    assertNull(order.getClient());
                else
                    assertNotNull(order.getClient());
            }
        } catch (Exception e) {
            fail("Bad deserialization");
        }
    }
}