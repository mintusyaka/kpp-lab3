package views.generators;

import managers.ClientManager;
import models.Client;
import views.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientsGeneratorView implements Menu {

    Menu prevMenu;

    List<Client> clients;

    public ClientsGeneratorView(List<Client> clients, Menu prevMenu) {
        this.clients = clients;
        this.prevMenu = prevMenu;

        if(clients == null)
            clients = new ArrayList<>();
    }

    @Override
    public Menu display() {

        return null;
    }

    private void showMenu()
    {
        System.out.println("\t\tGENERATE CLIENTS");
        System.out.println("Are you sure that you want to generate a new clients list? (\"yes\" or \"no\")");
    }

    private Menu getAnswer()
    {
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        switch (answer) {
            case "yes":
                ClientManager.ClientsGenerator.generate(clients);
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
