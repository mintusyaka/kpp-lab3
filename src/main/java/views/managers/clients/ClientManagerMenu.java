package views.managers.clients;

import models.Client;
import views.Menu;
import views.generators.ClientsGeneratorView;
import views.managers.dishes.DeleteDishMenu;

import java.util.List;
import java.util.Scanner;

public class ClientManagerMenu implements Menu {

    Menu prevMenu;

    List<Client> clients;


    public ClientManagerMenu(List<Client> clients, Menu prevMenu) {
        this.clients = clients;
        this.prevMenu = prevMenu;
    }

    @Override
    public Menu display() {
        showMenu();
        return getAnswer();
    }

    private void showMenu() {
        System.out.println("\t\tCLIENT MANAGE MENU");
        System.out.println("Choose option: ");
        System.out.println("\"generate\" - to generate clients");
        System.out.println("\"add\" - to add clients");
        System.out.println("\"delete\" - to delete clients");
        System.out.println("\"show\" - to show clients");
        System.out.println("\"exit\" - to exit");
        System.out.print("Your decision (write without double quotes): ");
    }

    private Menu getAnswer() {
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        switch (answer) {
            case "generate":
                return new ClientsGeneratorView(clients, prevMenu);
            case "add":
                return new AddClientMenu(clients, this);
            case "delete":
                return new DeleteClientMenu(clients, this);
            case "show":
                for(Client client : clients) {
                    System.out.println(client);
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
