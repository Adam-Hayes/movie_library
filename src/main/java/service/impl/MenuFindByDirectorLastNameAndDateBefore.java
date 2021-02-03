package service.impl;

import domain.Film;
import repository.FilmRepository;
import repository.FilmRepositoryImpl;
import service.FilmService;
import service.FilmServiceImpl;
import service.MenuAction;
import Utills.ScannerWrapper;

import java.util.List;

import static service.impl.Constants.MESSAGE_FOR_DATE_BEFORE;
import static service.impl.Constants.MESSAGE_FOR_DIRECTOR_LAST_NAME;

public class MenuFindByDirectorLastNameAndDateBefore implements MenuAction {
    @Override
    public List<Film> search(ScannerWrapper scannerWrapper) {
        System.out.println(MESSAGE_FOR_DIRECTOR_LAST_NAME);
        String directorLastName = scannerWrapper.readLine();
        System.out.println(MESSAGE_FOR_DATE_BEFORE);
        String dateBefore = scannerWrapper.readLine();
        FilmRepository filmRepository = new FilmRepositoryImpl();
        FilmService filmService = new FilmServiceImpl(filmRepository);
        return filmService.findByDirectorLastNameAndDateBefore(directorLastName,dateBefore) ;
    }
}
