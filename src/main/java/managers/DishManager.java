package managers;

import models.Dish;
import models.DishMenu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DishManager {
    public static void writeDishesToFile(List<Dish> dishes, String fileName) {
        try(FileWriter dishWriter = new FileWriter(fileName, false))
        {
            for(Dish dish : dishes)
            {
                dishWriter.write(dish.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Dish> readDishesFromFile(String fileName, DishMenu dishMenu) {
        List<Dish> dishes = new ArrayList<>();
        try(FileReader reader = new FileReader(fileName))
        {
            int character = 0;
            String buffer = "";
            while((character = reader.read()) != -1)
            {
                buffer += (char) character;
                if(character == '\n') {
                    if(buffer.length() <= 1)
                        break;
                    String[] lines = buffer.split("\\|");
                    boolean haveAllergens = false;
                    if(lines[2].contains("true"))
                        haveAllergens = true;

                    Dish dish = new Dish(lines[0].split(":")[1], lines[1].split(":")[1], haveAllergens);

                    dishes.add(dish);

                    buffer = "";
                }
            }
            dishMenu.setDishes(dishes);
        } catch (IOException e) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Error reading menu. Do you want to generate new one?");
                String command = scanner.nextLine();
                if(command.equals("yes"))
                {
                    MenuGenerator.generate(dishMenu);
                }
                else {
                    System.out.println("Bye-bye!");
                    return null;
                }
            }
            return dishes;
        }

    public static class MenuGenerator {
        static List<Dish> dishes = new ArrayList<Dish>();

        private static void fillDishes()
        {
            if(dishes.isEmpty())
            {
                dishes.add(
                        new Dish(
                                "Spaghetti Bolognese",
                                "Pasta with meat sauce",
                                false
                        )
                );
                dishes.add(
                        new Dish(
                                "Caesar Salad",
                                "Salad with lettuce, croutons, and Caesar dressing",
                                true
                        )
                );
                dishes.add(
                        new Dish(
                                "Margherita Pizza",
                                "Classic pizza with tomato, mozzarella, and basil",
                                false
                        )
                );
                dishes.add(
                        new Dish(
                                "Vegetable Stir Fry",
                                "Mixed vegetables stir-fried with soy sauce",
                                true
                        )
                );
                dishes.add(
                        new Dish(
                                "Grilled Salmon",
                                "Salmon fillet grilled with lemon and herbs",
                                false
                        )
                );
                dishes.add(
                        new Dish(
                                "Mushroom Risotto",
                                "Creamy risotto with mushrooms and parmesan cheese",
                                true
                        )
                );
                dishes.add(
                        new Dish(
                                "BBQ Ribs",
                                "Pork ribs with a smoky barbecue sauce",
                                false
                        )
                );
                dishes.add(
                        new Dish(
                                "Tom Yum Soup",
                                "Spicy and sour Thai soup with shrimp",
                                false
                        )
                );
                dishes.add(
                        new Dish(
                                "Falafel Wrap",
                                "Wrap with falafel, hummus, and fresh veggies",
                                true
                        )
                );
                dishes.add(
                        new Dish(
                                "Ice Cream Sundae",
                                "Vanilla ice cream topped with chocolate syrup and nuts",
                                true
                        )
                );
            }
        }

        public static void generate(DishMenu dishMenu) {
            fillDishes();
            dishMenu.setDishes(dishes);
        }
    }
}
