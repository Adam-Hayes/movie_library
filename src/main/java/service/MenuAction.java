package service;

import Utills.ScannerWrapper;
import domain.Film;

import java.util.List;

public interface MenuAction {
    List<Film> search(ScannerWrapper scannerWrapper);

}
