package views.managers.orders;

import models.Client;
import models.DishMenu;
import models.Order;
import views.Menu;
import views.generators.OrderGeneratorView;

import java.util.List;
import java.util.Scanner;

public class OrderManagerMenu implements Menu {

    Menu prevMenu;

    DishMenu menu;
    List<Client> clients;
    List<Order> orders;

    public OrderManagerMenu(DishMenu menu, List<Client> clients, List<Order> orders, Menu prevMenu) {
        this.prevMenu = prevMenu;
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
        System.out.println("\t\tORDERS MANAGE MENU");
        System.out.println("Choose option: ");
        System.out.println("\"generate\" - to generate orders");
        System.out.println("\"add\" - to add new order");
        System.out.println("\"delete\" - to delete order");
        System.out.println("\"show\" - to show orders");
        System.out.println("\"exit\" - to exit");
        System.out.print("Your decision (write without double quotes): ");
    }

    private Menu getAnswer() {
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        switch (answer) {
            case "generate":
                return new OrderGeneratorView(menu, clients, orders, prevMenu);
            case "add":
                return new AddOrderMenu(menu, clients, orders, this);
            case "delete":
                return new DeleteOrderMenu(orders, this);
            case "show":
                int i = 0;
                for(Order order : orders) {
                    System.out.println(order);
                }
                return this;
            case "exit":
                return prevMenu;
            default:
                showErrorMsg();
                return this;
        }
    }

    private void showErrorMsg()
    {
        System.out.println("You have entered an invalid choice. Please try again.");
    }}
