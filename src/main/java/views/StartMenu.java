package views;

/*

 */

import models.Client;
import models.DishMenu;
import models.Order;
import serializers.JsonOrdersSerializer;
import serializers.NativeOrdersSerializer;
import serializers.YamlOrdersSerializer;
import views.managers.ManageMenu;

import java.util.List;
import java.util.Scanner;

public class StartMenu implements Menu {

    DishMenu menu;
    List<Client> clients;
    List<Order> orders;

    public StartMenu(DishMenu menu, List<Client> clients, List<Order> orders) {
        this.menu = menu;
        this.clients = clients;
        this.orders = orders;
    }

    @Override
    public Menu display() {
        showMenu();
        return getAnswer();
    }

    private void showMenu() {
        System.out.println("\t\tSTART MENU");
        System.out.println("Welcome! Choose serializer: ");
        System.out.println("\"native\" - for Native Serialization");
        System.out.println("\"json\" - for JSON Serialization");
        System.out.println("\"yaml\" - for YAML Serialization");
        System.out.println("\"exit\" - to exit");
        System.out.print("Your decision (write without double quotes): ");
    }

    private Menu getAnswer() {
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

        clients.clear();
        menu.getDishes().clear();
        orders.clear();

        switch (answer) {
            case "native":
                return new ManageMenu(menu, clients, orders, new NativeOrdersSerializer(), this);
            case "json":
                return new ManageMenu(menu, clients, orders, new JsonOrdersSerializer(), this);
            case "yaml":
                return new ManageMenu(menu, clients, orders, new YamlOrdersSerializer(), this);
            case "exit":
                return new ExitMenu(this, null);
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
