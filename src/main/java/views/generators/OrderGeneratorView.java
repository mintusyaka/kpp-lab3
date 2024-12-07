package views.generators;

import models.Client;
import models.DishMenu;
import models.Order;
import serializers.OrdersSerializer;
import views.Menu;

import java.util.List;
import java.util.Scanner;

public class OrderGeneratorView implements Menu {

    Menu prevMenu;

    DishMenu menu;
    List<Client> clients;
    List<Order> orders;

    public OrderGeneratorView(DishMenu menu, List<Client> clients, List<Order> orders, Menu prevMenu) {
        this.menu = menu;
        this.clients = clients;
        this.orders = orders;
        this.prevMenu = prevMenu;
    }


    @Override
    public Menu display() {
        showMenu();
        return getAnswer();
    }

    private void showMenu()
    {
        System.out.println("\t\tGENERATE ORDER");
        System.out.println("Are you sure that you want to generate a new order? (\"yes\" or \"no\")");
    }

    private Menu getAnswer()
    {
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        switch (answer) {
            case "yes":
                OrdersSerializer.OrdersGenerator.generate(orders, clients, menu);
                return prevMenu;
            case "no":
                return prevMenu;
            default:
                showErrorMsg();
                return this;
        }
    }

    private void showErrorMsg() {
        System.out.println("Please enter a valid option!");
    }
}
