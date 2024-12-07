package views.managers.orders;

import models.Client;
import models.Dish;
import models.DishMenu;
import models.Order;
import views.Menu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class AddOrderMenu implements Menu {
    Menu prevMenu;

    DishMenu menu;
    List<Client> clients;
    List<Order> orders;

    public AddOrderMenu(DishMenu menu, List<Client> clients, List<Order> orders, Menu prevMenu) {
        this.prevMenu = prevMenu;
        this.orders = orders;
        this.menu = menu;
        this.clients = clients;
    }

    @Override
    public Menu display() {
        showMenu();
        return getAnswer();
    }

    private void showMenu() {
        System.out.println("\t\tADD NEW ORDER MENU");
    }

    private void enterDataMsg(Order order)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print(" - All available clients:");
        for(Client client : clients)
        {
            System.out.println(client);
        }
        System.out.print("Select client by id: ");

        int id = scanner.nextInt();
        Client c = clients.stream().filter(client -> client.getId() == id)
                .findFirst().get();
        order.setClient(c);

        System.out.print("Select dishes by number:\n");
        int i = 0;
        for(Dish dish : menu.getDishes())
        {
            System.out.println(i++ + ". " + dish);
        }

        order.setDishes(new ArrayList<>());
        String answer = "";

        while(!answer.equals("stop"))
        {
            System.out.print("Enter number to add dish to order or write \"stop\" to stop:\n");
            answer = scanner.nextLine();
            try {
                order.addDish(menu.getDishes().get(Integer.parseInt(answer)));
            } catch (NumberFormatException e) {
                System.out.println("Wrong command. Please try again.");
            }
        }
        order.setTableId(c.getTableId());

        int orderId = orders.stream().max(Comparator.comparingInt(Order::getId)).get().getId() + 1;
        order.setId(orderId);

        orders.add(order);
    }

    private Menu getAnswer() {
        Order order = new Order();
        enterDataMsg(order);
        return prevMenu;
    }
}
