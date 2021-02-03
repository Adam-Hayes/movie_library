package repository;

import config.DataSource;
import domain.Film;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilmRepositoryImplTest {
    public static final String NAME = "val1";

    public static final String GENRE = "val2";
    public static final String FIRST_NAME = "val3";
    public static final String LAST_NAME = "val4";
    public static final String RELEASE_DATE = "2020-12-25";
    public static final String BIRTH_DATE = "2000-05-15";
    public static final String INVALID_DATA = "123";
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSet;
    @Mock
    private DataSource dataSource;
    @Mock
    private Connection connection;

    private FilmRepositoryImpl filmRepositoryImpl;


    @BeforeEach
    void setUp() throws SQLException {
        filmRepositoryImpl = new FilmRepositoryImpl();
        filmRepositoryImpl.setDataSource(dataSource);
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
    }

    @Test
    public void findByPeriod_shouldReturnFilmList() throws SQLException {

        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString(any())).thenReturn(NAME, GENRE, FIRST_NAME, LAST_NAME);
        when(resultSet.getDate(any())).thenReturn(Date.valueOf(RELEASE_DATE), Date.valueOf(BIRTH_DATE));

        List<Film> actualFilmList = filmRepositoryImpl.findByPeriodOfDate(RELEASE_DATE, BIRTH_DATE);
        Film actualFilm = actualFilmList.get(0);

        assertEquals(1, actualFilmList.size());

        assertEquals(NAME, actualFilm.getName());
        assertEquals(GENRE, actualFilm.getGenre());
        assertEquals(LocalDate.parse(RELEASE_DATE), actualFilm.getReleaseDate());
        assertEquals(FIRST_NAME, actualFilm.getDirector().getFirstName());
        assertEquals(LAST_NAME, actualFilm.getDirector().getLastName());
        assertEquals(LocalDate.parse(BIRTH_DATE), actualFilm.getDirector().getBirthDate());

        verify(preparedStatement, times(1)).executeQuery();
    }

    @Test
    public void findByPeriod_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> filmRepositoryImpl.findByPeriodOfDate(INVALID_DATA, INVALID_DATA));
    }

    @Test
    public void findByDateAfter_shouldReturnFilmList() throws SQLException {

        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString(any())).thenReturn(NAME, GENRE, FIRST_NAME, LAST_NAME);
        when(resultSet.getDate(any())).thenReturn(Date.valueOf(RELEASE_DATE), Date.valueOf(BIRTH_DATE));

        List<Film> actualFilmList = filmRepositoryImpl.findByDateAfter(RELEASE_DATE);
        Film actualFilm = actualFilmList.get(0);

        assertEquals(1, actualFilmList.size());

        assertEquals(NAME, actualFilm.getName());
        assertEquals(GENRE, actualFilm.getGenre());
        assertEquals(LocalDate.parse(RELEASE_DATE), actualFilm.getReleaseDate());
        assertEquals(FIRST_NAME, actualFilm.getDirector().getFirstName());
        assertEquals(LAST_NAME, actualFilm.getDirector().getLastName());
        assertEquals(LocalDate.parse(BIRTH_DATE), actualFilm.getDirector().getBirthDate());

        verify(preparedStatement, times(1)).executeQuery();
    }

    @Test
    public void findByDateAfter_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> filmRepositoryImpl.findByDateAfter(INVALID_DATA));
    }

    @Test
    public void findByDateBefore_shouldReturnFilmList() throws SQLException {

        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString(any())).thenReturn(NAME, GENRE, FIRST_NAME, LAST_NAME);
        when(resultSet.getDate(any())).thenReturn(Date.valueOf(RELEASE_DATE), Date.valueOf(BIRTH_DATE));

        List<Film> actualFilmList = filmRepositoryImpl.findByDateBefore(RELEASE_DATE);
        Film actualFilm = actualFilmList.get(0);

        assertEquals(1, actualFilmList.size());

        assertEquals(NAME, actualFilm.getName());
        assertEquals(GENRE, actualFilm.getGenre());
        assertEquals(LocalDate.parse(RELEASE_DATE), actualFilm.getReleaseDate());
        assertEquals(FIRST_NAME, actualFilm.getDirector().getFirstName());
        assertEquals(LAST_NAME, actualFilm.getDirector().getLastName());
        assertEquals(LocalDate.parse(BIRTH_DATE), actualFilm.getDirector().getBirthDate());

        verify(preparedStatement, times(1)).executeQuery();
    }

    @Test
    public void findByDateBefore_shouldThrowIllegalArgumentException() {

        assertThrows(IllegalArgumentException.class, () -> filmRepositoryImpl.findByDateBefore(INVALID_DATA));
    }


    @Test
    public void findByDirectorLastName_shouldReturnFilmList() throws SQLException {

        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString(any())).thenReturn(NAME, GENRE, FIRST_NAME, LAST_NAME);
        when(resultSet.getDate(any())).thenReturn(Date.valueOf(RELEASE_DATE), Date.valueOf(BIRTH_DATE));

        List<Film> actualFilmList = filmRepositoryImpl.findByDirectorLastName(FIRST_NAME);
        Film actualFilm = actualFilmList.get(0);

        assertEquals(1, actualFilmList.size());

        assertEquals(NAME, actualFilm.getName());
        assertEquals(GENRE, actualFilm.getGenre());
        assertEquals(LocalDate.parse(RELEASE_DATE), actualFilm.getReleaseDate());
        assertEquals(FIRST_NAME, actualFilm.getDirector().getFirstName());
        assertEquals(LAST_NAME, actualFilm.getDirector().getLastName());
        assertEquals(LocalDate.parse(BIRTH_DATE), actualFilm.getDirector().getBirthDate());

        verify(preparedStatement, times(1)).executeQuery();
    }


}