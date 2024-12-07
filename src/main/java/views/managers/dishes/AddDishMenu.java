package views.managers.dishes;

import models.Client;
import models.Dish;
import models.DishMenu;
import views.Menu;

import java.util.Scanner;

public class AddDishMenu implements Menu {
    Menu prevMenu;

    DishMenu dishes;

    public AddDishMenu(DishMenu menu, Menu prevMenu) {
        this.prevMenu = prevMenu;
        this.dishes = menu;
    }

    @Override
    public Menu display() {
        showMenu();
        return getAnswer();
    }

    private void showMenu() {
        System.out.println("\t\tADD NEW DISH MENU");
    }

    private void enterDataMsg(Dish dish)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter dish title: ");
        String answer = scanner.nextLine();
        dish.setTitle(answer);
        System.out.print("Enter dish description: ");
        answer = scanner.nextLine();
        dish.setDescription(answer);
        while(!answer.contains("have") && !answer.contains("not"))
        {
            System.out.print("Enter \"have\" - is dish have allergens and \"not\" - is not: ");
            answer = scanner.nextLine();
            if(answer.contains("have"))
            {
                dish.setHaveAllergens(true);
            }
            else if(answer.contains("not"))
            {
                dish.setHaveAllergens(false);
            }
            else {
                System.out.println("Wrong output. Please try again.");
            }
        }

        dishes.addDish(dish);
    }

    private Menu getAnswer() {
        Dish dish = new Dish();
        enterDataMsg(dish);
        return prevMenu;
    }
}
