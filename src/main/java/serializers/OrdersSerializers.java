package serializers;

import models.Order;

import java.util.List;

public interface OrdersSerializers {
    public void serialize(List<Order> orders, String filename) throws Exception;

    public List<Order> deserialize(String filename) throws Exception;
}
