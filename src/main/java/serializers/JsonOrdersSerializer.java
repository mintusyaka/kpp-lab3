package serializers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import models.Order;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JsonOrdersSerializer implements OrdersSerializers{
    @Override
    public void serialize(List<Order> orders, String filename) throws Exception {
        File file = new File(filename);
        if(file.exists() && file.delete()) { }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try(FileWriter fw = new FileWriter(filename)) {
            gson.toJson(orders, fw);
        } catch (IOException e) {
            throw new Exception("Cannot serialize orders");
        }
    }

    @Override
    public List<Order> deserialize(String filename) throws Exception {
        try(FileReader fr = new FileReader(filename)) {
            Type listOfOrderType = new TypeToken<List<Order>>() {}.getType();
            Gson gson = new Gson();
            List<Order> orders = gson.fromJson(fr, listOfOrderType);

            Set<Integer> seenIds = new HashSet<>();

            List<Order> filteredOrders = orders.stream()
                    .filter(order -> seenIds.add(order.getId())) // Add `id` to `seenIds` only if it’s not already present
                    .collect(Collectors.toList());

            return filteredOrders;
        } catch (IOException e) {
            throw new Exception("Cannot deserialize orders");
        }
    }
}
