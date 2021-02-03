import domain.Film;
import service.MenuAction;
import factory.MenuActionFactory;
import Utills.ScannerWrapper;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        MenuActionFactory menuActionFactory = new MenuActionFactory();
        ScannerWrapper scannerWrapper = new ScannerWrapper();
        int choice = 0;

        while (true) {

            System.out.print("Please, select a method of search:" + "\n" +
                    "1 - Search by director's last name" + "\n" +
                    "2 - Search by date after" + "\n" +
                    "3 - Search by date before" + "\n" +
                    "4 - Search by period of dates" + "\n" +
                    "5 - Search by director's last name and date after" + "\n" +
                    "6 - Search by director's last name and date before" + "\n" +
                    "7 - Search by director's last name and period of dates" + "\n" +
                    "0 - Exit" + "\n");
            try {
                choice = Integer.parseInt(scannerWrapper.readLine());

                if (choice > 7 || choice < 0) {
                    throw new IllegalArgumentException("Incorrect value");
                }

            } catch (IllegalArgumentException e) {
                System.err.println("Enter correct number");
                continue;
            }
            MenuAction action = menuActionFactory.getAction(choice);
            List<Film> filmList = action.search(scannerWrapper);
            printResult(filmList);


        }
    }

    private static void printResult(List<Film> filmList) {
        if (filmList.isEmpty()) {
            System.out.println("Director don't release a film");
        } else {
            filmList.forEach(System.out::println);
        }
    }

}

