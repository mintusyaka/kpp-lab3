import managers.ClientManager;
import managers.DishManager;
import models.Client;
import models.Dish;
import models.DishMenu;
import models.Order;
import views.Menu;
import views.StartMenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DishMenu dishMenu = new DishMenu();
        List<Client> clients = new ArrayList<>();
        List<Order> orders = new ArrayList<>();

        Menu menu = new StartMenu(dishMenu, clients, orders);

        while (menu != null) {
            menu = menu.display();
        }
/*
        DishManager.readDishesFromFile("menu.txt", dishMenu);
        if(dishMenu.getDishes() == null || dishMenu.getDishes().isEmpty())
            return;

        ClientManager.readClientsFromFile("clients.txt", clients);
        if(clients == null || clients.isEmpty())
        {
            return;
        }

        OrderManager.readOrdersFromFileByNativeSerialization(orders, clients, dishMenu, "orders.ser");
        if(orders == null || orders.isEmpty())
        {
            return;
        }



        *//*try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("menu.ser")))
        {
            menu = (Menu)ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Error reading menu. Do you want to generate new one?");
            String command = scanner.nextLine();
            if(command.equals("yes"))
            {
                MenuGenerator.generate(menu);
            }
            else {
                System.out.println("Bye-bye!");
                return;
            }
        }*//*



        while(true)
        {
            String command = scanner.nextLine();

            if(command.equals("exit"))
            {
                break;
            }

            int i = 0;
            for(Dish dish : dishMenu.getDishes())
            {
                System.out.println(i++ + "." + dish.getTitle());
                System.out.println(dish.getDescription());
                System.out.println("Allergens: " + dish.getHaveAllergens());
                System.out.println();
            }

            for(Order order : orders) {
                System.out.println(order);
            }
        }

        *//*try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("menu.ser"))) {
            oos.writeObject(menu);
            System.out.println("Successfully serialized menu");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } ;*//*

        DishManager.writeDishesToFile(dishMenu.getDishes(), "menu.txt");
        ClientManager.writeClientsToFile(clients, "clients.txt");
        OrderManager.writeOrdersToFileByNativeSerialization(orders, "orders.ser");*/
    }
}
