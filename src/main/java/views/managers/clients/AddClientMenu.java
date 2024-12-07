package views.managers.clients;

import models.Client;
import views.Menu;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class AddClientMenu implements Menu {
    Menu prevMenu;

    List<Client> clients;

    public AddClientMenu(List<Client> clients, Menu prevMenu) {
        this.prevMenu = prevMenu;
        this.clients = clients;
    }

    @Override
    public Menu display() {
        showMenu();
        return getAnswer();
    }

    private void showMenu() {
        System.out.println("\t\tADD NEW CLIENT MENU");
    }

    private void enterDataMsg(Client client)
    {
        System.out.print("Enter client name: ");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        client.setName(answer);
        System.out.print("Enter client table number: ");
        int number = scanner.nextInt();
        client.setTableId(number);

        int id = clients.stream().max(Comparator.comparingInt(Client::getId)).get().getId() + 1;
        client.setId(id);
        clients.add(client);
    }

    private Menu getAnswer() {
        Client client = new Client();
        enterDataMsg(client);
        return prevMenu;
    }
}
