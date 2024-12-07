package views.managers.clients;

import models.Client;
import views.Menu;

import java.util.List;
import java.util.Scanner;

public class DeleteClientMenu implements Menu {
    Menu prevMenu;

    List<Client> clients;

    public DeleteClientMenu(List<Client> clients, Menu prevMenu) {
        this.prevMenu = prevMenu;
        this.clients = clients;
    }

    @Override
    public Menu display() {
        showMenu();
        return getAnswer();
    }

    private void showMenu() {
        System.out.println("\t\tDELETE CLIENT MENU");
    }

    private void enterDataMsg()
    {
        System.out.print("Enter client id or name to delete: ");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        try{
           int id = Integer.parseInt(answer);
            Client c = clients.stream().filter(client -> client.getId() == id)
                    .findFirst().get();
            clients.remove(c);
            return;
        }
        catch (NumberFormatException e)
        {
            Client c = clients.stream().filter(client -> answer.contains(client.getName())).findFirst().get();
            clients.remove(c);
            return;
        }
    }

    private Menu getAnswer() {
        enterDataMsg();
        return prevMenu;
    }
}
