package serializers;

import models.Client;
import models.Dish;
import models.DishMenu;
import models.Order;

import java.util.*;

public class OrdersSerializer {
    OrdersSerializers serializer;

    public OrdersSerializer(OrdersSerializers serializer) {
        this.serializer = serializer;
    }

    public void serialize(List<Order> orders, String filename) {
        try {
            serializer.serialize(orders, filename);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public List<Order> deserialize(List<Order> orders, List<Client> clients, DishMenu menu, String filename) {
        try {
            orders.addAll(serializer.deserialize(filename));
            return orders;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static class OrdersGenerator {
        private static void fillOrders(List<Order> orders, List<Client> clients, DishMenu dishMenu)
        {
            Random rand = new Random();
            for(Client client : clients) {
                if(rand.nextBoolean()) continue;

                List<Dish> shuffledList = new ArrayList<>(dishMenu.getDishes());

                // Shuffle the list
                Collections.shuffle(shuffledList);

                int randRightIdx = rand.nextInt(shuffledList.size() - 1);
                if(randRightIdx == 0)
                    randRightIdx = 1;
                // Get the first n elements
                List<Dish> randomSubList = shuffledList.subList(0, randRightIdx);

                orders.add(new Order(
                        orders.size(),
                        client,
                        client.getTableId()
                ));

                orders.get(orders.size() - 1).setDishes(randomSubList);
            }
            if(!orders.isEmpty())
                orders.get(orders.size() - 1).setDishes(dishMenu.getDishes());
        }

        public static void generate(List<Order> orders, List<Client> clients, DishMenu dishMenu) {
            if(clients == null || clients.isEmpty() ||
                    dishMenu.getDishes() == null || dishMenu.getDishes().isEmpty()) { }
            else fillOrders(orders, clients, dishMenu);
        }
    }
}
