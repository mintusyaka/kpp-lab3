package views.managers.dishes;

import models.Dish;
import views.Menu;

import java.util.List;
import java.util.Scanner;

public class DeleteDishMenu implements Menu {
    Menu prevMenu;

    List<Dish> dishes;

    public DeleteDishMenu(List<Dish> dishes, Menu prevMenu) {
        this.prevMenu = prevMenu;
        this.dishes = dishes;
    }

    @Override
    public Menu display() {
        showMenu();
        return getAnswer();
    }

    private void showMenu() {
        System.out.println("\t\tDELETE DISH MENU");
    }

    private void enterDataMsg()
    {
        System.out.print("Enter dish number or title to delete: ");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        try{
            int id = Integer.parseInt(answer);
            dishes.remove(id);
            return;
        }
        catch (NumberFormatException e)
        {
            Dish d = dishes.stream().filter(dish -> answer.contains(dish.getTitle())).findFirst().get();
            dishes.remove(d);
            return;
        }
    }

    private Menu getAnswer() {
        enterDataMsg();
        return prevMenu;
    }
}
