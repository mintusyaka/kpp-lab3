package serializers;

import models.Client;
import models.Dish;
import models.Order;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class YamlOrdersSerializer implements OrdersSerializers{
    @Override
    public void serialize(List<Order> orders, String filename) throws Exception {
        DumperOptions options = new DumperOptions();
        options.setIndent(4); // Відступи для читабельності
        options.setPrettyFlow(true); // Лінійний вигляд YAML
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK); // Блочний стиль


        List<Order> ordersByTask = new ArrayList<>();
        for (Order order : orders) {
            if(order.getDishes() != null && order.getDishes().size() > 3) {
                Order o = new Order(order.getId(), null, order.getTableId());
                o.setDishes(order.getDishes());

                ordersByTask.add(o);
            } else ordersByTask.add(order);
        }

        Yaml yaml = new Yaml();

        try (FileWriter writer = new FileWriter(filename)) {
                yaml.dump(ordersByTask, writer);
        } catch (IOException e) {
            throw new Exception("Cannot serialize orders");
        }
    }

    @Override
    public List<Order> deserialize(String filename) throws Exception {
        LoaderOptions loaderOptions = new LoaderOptions();
        loaderOptions.setTagInspector(tag -> {
            return tag.startsWith("tag:yaml.org,2002:")
                    || tag.equals("!!models.Order")
                    || tag.equals("!!models.Client");
        });

        Yaml yaml = new Yaml(loaderOptions);

        try (FileReader reader = new FileReader(filename)) {
            // Десеріалізація списку об'єктів Order
            return yaml.loadAs(reader, List.class);
        } catch (IOException e) {
            throw new Exception("Cannot deserialize orders", e);
        }
    }
}
