package service;

import domain.Film;

import java.util.List;

public interface FilmService {

    List<Film> findByPeriodOfDate(String dateAfter, String dateBefore);
    List<Film> findByDateBefore(String dateBefore);
    List<Film> findByDateAfter(String dateAfter);
    List<Film> findByDirectorLastName(String directorLastName);
    List<Film> findByDirectorLastNameAndPeriodOfDate(String directorLastName, String dateAfter, String dateBefore);
    List<Film> findByDirectorLastNameAndDateAfter(String directorLastName, String dateAfter);
    List<Film> findByDirectorLastNameAndDateBefore(String directorLastName, String dateBefore);
}
