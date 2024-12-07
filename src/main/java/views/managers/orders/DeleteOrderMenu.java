package views.managers.orders;

import models.Client;
import models.Dish;
import models.Order;
import views.Menu;

import java.util.List;
import java.util.Scanner;

public class DeleteOrderMenu implements Menu {
    Menu prevMenu;

    List<Order> orders;

    public DeleteOrderMenu(List<Order> orders, Menu prevMenu) {
        this.prevMenu = prevMenu;
        this.orders = orders;
    }

    @Override
    public Menu display() {
        showMenu();
        return getAnswer();
    }

    private void showMenu() {
        System.out.println("\t\tDELETE ORDER MENU");
    }

    private void enterDataMsg()
    {
        System.out.print("Enter order id: ");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        try{
            int id = Integer.parseInt(answer);
            Order c = orders.stream().filter(order -> order.getId() == id)
                    .findFirst().get();
            orders.remove(c);
            return;
        }
        catch (NumberFormatException e)
        {
            System.out.println("Wrong id. Try again.");
            return;
        }
    }

    private Menu getAnswer() {
        enterDataMsg();
        return prevMenu;
    }
}
