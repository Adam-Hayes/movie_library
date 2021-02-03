package service.impl;

import Utills.ScannerWrapper;
import domain.Film;
import repository.FilmRepository;
import repository.FilmRepositoryImpl;
import service.FilmService;
import service.FilmServiceImpl;
import service.MenuAction;

import java.util.List;

import static service.impl.Constants.MESSAGE_FOR_DATE_BEFORE;

public class MenuFindByDateBeforeImpl implements MenuAction {

    @Override
    public List<Film> search(ScannerWrapper scannerWrapper) {
        System.out.println(MESSAGE_FOR_DATE_BEFORE);
        String dateBefore = scannerWrapper.readLine();

        FilmRepository filmRepository = new FilmRepositoryImpl();
        FilmService filmService = new FilmServiceImpl(filmRepository);

        return filmService.findByDateBefore(dateBefore);
    }
}
