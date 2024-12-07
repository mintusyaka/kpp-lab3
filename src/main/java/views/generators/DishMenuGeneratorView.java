package views.generators;

import managers.DishManager;
import models.DishMenu;
import views.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class DishMenuGeneratorView implements Menu {

    Menu prevMenu;

    DishMenu menu;

    public DishMenuGeneratorView(DishMenu menu, Menu prevMenu) {
        this.menu = menu;
        this.prevMenu = prevMenu;

        if(menu.getDishes() == null)
            menu.setDishes(new ArrayList<>());
    }

    @Override
    public Menu display() {

        return null;
    }

    private void showMenu()
    {
        System.out.println("\t\tGENERATE DISHES");
        System.out.println("Are you sure that you want to generate a new dishes? (\"yes\" or \"no\")");
    }

    private Menu getAnswer()
    {
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        switch (answer) {
            case "yes":
                DishManager.MenuGenerator.generate(menu);
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
