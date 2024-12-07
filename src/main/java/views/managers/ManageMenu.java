package views.managers;

import managers.ClientManager;
import managers.DishManager;
import models.Client;
import models.DishMenu;
import models.Order;
import serializers.*;
import views.ExitMenu;
import views.Menu;
import views.generators.ClientsGeneratorView;
import views.generators.DishMenuGeneratorView;
import views.generators.OrderGeneratorView;
import views.managers.clients.ClientManagerMenu;
import views.managers.dishes.DishManagerMenu;
import views.managers.orders.OrderManagerMenu;

import java.util.List;
import java.util.Scanner;

public class ManageMenu implements Menu {

    Menu prevMenu;

    DishMenu menu;
    List<Client> clients;
    List<Order> orders;

    OrdersSerializer orderManager;

    String typeOfFile;

    boolean generateOrdersPropose;
    boolean generateClientsPropose;
    boolean generateDishesPropose;

    public ManageMenu(DishMenu menu, List<Client> clients,
                      List<Order> orders, OrdersSerializers ordersSerializer,
                      Menu prevMenu) {
        this.menu = menu;
        this.clients = clients;
        this.orders = orders;
        this.prevMenu = prevMenu;
        orderManager = new OrdersSerializer(ordersSerializer);

        generateOrdersPropose = false;
        generateClientsPropose = false;
        generateDishesPropose = false;

        if(ordersSerializer.getClass() == NativeOrdersSerializer.class)
            typeOfFile = "ser";
        else if(ordersSerializer.getClass() == JsonOrdersSerializer.class)
            typeOfFile = "json";
        else if(ordersSerializer.getClass() == YamlOrdersSerializer.class)
            typeOfFile = "yaml";
        else throw new RuntimeException("Unsupported type");

        DishManager.readDishesFromFile("menu.txt", menu);
        ClientManager.readClientsFromFile("clients.txt", clients);
        orderManager.deserialize(orders, clients, menu, "orders." + typeOfFile);
    }

    @Override
    public Menu display() {
        if((menu.getDishes() == null || menu.getDishes().isEmpty()) && !generateDishesPropose)
        {
            generateDishesPropose = true;
            return new DishMenuGeneratorView(menu, this);
        }
        if((clients == null || clients.isEmpty()) && !generateClientsPropose)
        {
            generateClientsPropose = true;
            return new ClientsGeneratorView(clients, this);
        }
        if((orders == null || orders.isEmpty()) && !generateOrdersPropose)
        {
            generateOrdersPropose = true;
            return new OrderGeneratorView(menu, clients, orders, this);
        }
        showMenu();
        return getAnswer();
    }

    private void showMenu() {
        System.out.println("\t\tMANAGE MENU");
        System.out.println("Choose option: ");
        System.out.println("\"client\" - to manage clients");
        System.out.println("\"dish\" - to manage dishes");
        System.out.println("\"order\" - to manage orders");
        System.out.println("\"exit\" - to exit");
        System.out.print("Your decision (write without double quotes): ");
    }

    private Menu getAnswer() {
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        switch (answer) {
            case "client":
                return new ClientManagerMenu(clients, this);
            case "dish":
                return new DishManagerMenu(menu, this);
            case "order":
                return new OrderManagerMenu(menu, clients, orders, this);
            case "exit":
                DishManager.writeDishesToFile(menu.getDishes(), "menu.txt");
                ClientManager.writeClientsToFile(clients, "clients.txt");
                orderManager.serialize(orders, "orders." + typeOfFile);
                return new ExitMenu(this, prevMenu);
            default:
                showErrorMsg();
                return this;
        }
    }

    private void showErrorMsg()
    {
        System.out.println("You have entered an invalid choice. Please try again.");
    }
}
