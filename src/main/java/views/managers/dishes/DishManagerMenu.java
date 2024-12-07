package views.managers.dishes;

import models.Dish;
import models.DishMenu;
import views.Menu;
import views.generators.DishMenuGeneratorView;
import views.managers.dishes.DeleteDishMenu;

import java.util.Scanner;

public class DishManagerMenu implements Menu {

    Menu prevMenu;

    DishMenu menu;


    public DishManagerMenu(DishMenu menu, Menu prevMenu) {
        this.menu = menu;
        this.prevMenu = prevMenu;
    }

    @Override
    public Menu display() {
        showMenu();
        return getAnswer();
    }

    private void showMenu() {
        System.out.println("\t\tDISHES MANAGE MENU");
        System.out.println("Choose option: ");
        System.out.println("\"generate\" - to generate dishes");
        System.out.println("\"add\" - to add new dish");
        System.out.println("\"delete\" - to delete dish");
        System.out.println("\"show\" - to show dishes");
        System.out.println("\"exit\" - to exit");
        System.out.print("Your decision (write without double quotes): ");
    }

    private Menu getAnswer() {
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        switch (answer) {
            case "generate":
                return new DishMenuGeneratorView(menu, prevMenu);
            case "add":
                return new AddDishMenu(menu, this);
            case "delete":
                return new DeleteDishMenu(menu.getDishes(), this);
            case "show":
                int i = 0;
                for(Dish dish : menu.getDishes()) {
                    System.out.println(i++ + ". " + dish);
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
