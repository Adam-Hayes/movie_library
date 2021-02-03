package service;

import domain.Film;
import repository.FilmRepository;

import java.util.List;

public class FilmServiceImpl implements service.FilmService {
    private final FilmRepository filmRepository;


    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }


    @Override
    public List<Film> findByPeriodOfDate(String dateAfter, String dateBefore) {
        return filmRepository.findByPeriodOfDate(dateAfter, dateBefore);
    }


    @Override
    public List<Film> findByDateBefore(String dateBefore) {
        return filmRepository.findByDateBefore(dateBefore);
    }

    @Override
    public List<Film> findByDateAfter(String dateAfter) {
        return filmRepository.findByDateAfter(dateAfter);
    }

    @Override
    public List<Film> findByDirectorLastName(String directorName) {
        return filmRepository.findByDirectorLastName(directorName);
    }


    @Override
    public List<Film> findByDirectorLastNameAndPeriodOfDate(String directorLastName, String dateAfter, String dateBefore) {
        return filmRepository.findByDirectorLastNameAndPeriodOfDate(directorLastName,dateAfter, dateBefore);
    }

    @Override
    public List<Film> findByDirectorLastNameAndDateAfter(String directorLastName, String dateAfter) {
        return filmRepository.findByDirectorLastNameAndDateAfter(directorLastName,dateAfter);
    }

    @Override
    public List<Film> findByDirectorLastNameAndDateBefore(String directorLastName, String dateBefore) {
        return filmRepository.findByDirectorLastNameAndDateBefore(directorLastName,dateBefore);
    }
}
