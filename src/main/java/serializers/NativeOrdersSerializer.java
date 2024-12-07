package serializers;

import models.Order;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NativeOrdersSerializer implements OrdersSerializers{
    public void serialize(List<Order> orders, String filename) throws Exception {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            for(Order order : orders) {
                out.writeObject(order);
            }
        } catch (IOException e) {
            throw new Exception("Cannot serialize orders");
        }
    }

    public List<Order> deserialize(String filename) throws Exception {
        List<Order> orders = new ArrayList<>();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            while(true) {
                try {
                    Order order = (Order) in.readObject();
                    orders.add(order);
                } catch (IOException e) {
                    break;
                }
            }
            return orders;
        } catch (IOException | ClassNotFoundException e) {
            throw new Exception("Cannot deserialize orders");
        }
    }
}
