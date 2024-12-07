package views;

import java.util.Scanner;

public class ExitMenu implements Menu {
    Menu prevMenu;
    Menu menuToExit;

    public ExitMenu(Menu prevMenu, Menu menuToExit) {
        this.prevMenu = prevMenu;
        this.menuToExit = menuToExit;
    }

    @Override
    public Menu display() {
        showMenu();
        return getAnswer();
    }

    private void showMenu() {
        System.out.println("\t\tEXIT MENU");
        System.out.println("Are you sure you want to exit? (\"yes\" or \"no\")");
    }

    private Menu getAnswer() {
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        switch (answer) {
            case "yes":
                return menuToExit;
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
