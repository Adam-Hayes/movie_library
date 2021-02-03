package factory;

import service.MenuAction;
import service.impl.*;

public class MenuActionFactory {

    public MenuAction getAction(int choice) {
        MenuAction menuAction = null;

        switch (choice) {
            case 1://find by last name
                menuAction = new MenuFindByDirectorLastNameImpl();
                break;
            case 2://find by date after
                menuAction = new MenuFindByDateAfterImpl();
                break;
            case 3://find by date before
                menuAction = new MenuFindByDateBeforeImpl();
                break;
            case 4://find by period of dates
                menuAction = new MenuFindByPeriodOfDateImpl();
                break;
            case 5://find by last name and date after
                menuAction = new MenuFindByDirectorLastNameAndDateAfter();
                break;
            case 6://find by last name and date before
                menuAction = new MenuFindByDirectorLastNameAndDateBefore();
                break;
            case 7://find by last name and period of dates
                menuAction = new MenuFindByDirectorLastNameAndPeriodOfDate();
                break;
            case 0:
                System.exit(0);
            default:

        }

        return menuAction;
    }
}

