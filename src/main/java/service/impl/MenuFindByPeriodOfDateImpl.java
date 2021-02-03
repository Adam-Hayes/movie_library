package service.impl;

import domain.Film;
import repository.FilmRepository;
import repository.FilmRepositoryImpl;
import service.FilmService;
import service.FilmServiceImpl;
import service.MenuAction;
import Utills.ScannerWrapper;

import java.util.List;

import static service.impl.Constants.*;

public class MenuFindByPeriodOfDateImpl implements MenuAction {

    @Override
    public List<Film> search(ScannerWrapper scannerWrapper) {
        System.out.println(HEADER_FOR_DATES);
        System.out.println(MESSAGE_FOR_DATE_AFTER);
        String dateAfter = scannerWrapper.readLine();
        System.out.println(MESSAGE_FOR_DATE_BEFORE);
        String dateBefore = scannerWrapper.readLine();
        FilmRepository filmRepository = new FilmRepositoryImpl();
        FilmService filmService = new FilmServiceImpl(filmRepository);

        return filmService.findByPeriodOfDate(dateAfter, dateBefore);
    }
}
