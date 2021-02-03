package repository;

import config.DataSource;
import domain.Director;
import domain.Film;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import exception.SQLConnectionException;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class FilmRepositoryImpl implements FilmRepository {
    private static final String SELECT_BY_DATE_PERIOD_QUERY = "Select * from film inner join director on film.director_id=director.id where release_date between ?  and  ?";
    private static final String SELECT_BY_DATE_BEFORE_QUERY = "Select * from film inner join director on film.director_id=director.id where release_date < ?";
    private static final String SELECT_BY_DATE_AFTER_QUERY = "Select * from film inner join director on film.director_id=director.id where release_date > ?";
    private static final String SELECT_BY_DIRECTOR_LAST_NAME_QUERY = "Select * from film  inner join director on  film.director_id=director.id where director.last_name  ilike ?";
    private static final String SELECT_BY_DIRECTOR_LAST_NAME_AND_DATE_QUERY = "Select * from film  inner join director on  film.director_id=director.id where director.last_name  ilike ? and release_date between ? and ? ";
    private static final String SELECT_BY_DIRECTOR_LAST_NAME_AND_DATE_AFTER_QUERY = "Select * from film  inner join director on  film.director_id=director.id where director.last_name  ilike ? and release_date > ? ";
    private static final String SELECT_BY_DIRECTOR_LAST_NAME_AND_DATE_BEFORE_QUERY = "Select * from film  inner join director on  film.director_id=director.id where director.last_name  ilike ? and release_date < ? ";

    public static final Logger LOGGER = LoggerFactory.getLogger(FilmRepositoryImpl.class);

    private  DataSource dataSource = DataSource.getInstance();

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Film> findByPeriodOfDate(String dateAfter, String dateBefore) {
        List<Film> filmList ;

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_DATE_PERIOD_QUERY);

            preparedStatement.setDate(1, Date.valueOf(dateAfter));
            preparedStatement.setDate(2, Date.valueOf(dateBefore));

            ResultSet resultSet = preparedStatement.executeQuery();

            filmList = collectFilm(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Troubles with connection  {}", e.getMessage());
            throw new SQLConnectionException(e.getMessage(), e.getCause());

        }
        return filmList;
    }

    @Override
    public List<Film> findByDateBefore(String dateBefore) {
        List<Film> filmList;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_DATE_BEFORE_QUERY);
            preparedStatement.setDate(1, Date.valueOf(dateBefore));
            ResultSet resultSet = preparedStatement.executeQuery();
            filmList = collectFilm(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Troubles with connection  {}", e.getMessage());
            throw new SQLConnectionException(e.getMessage(), e.getCause());

        }
        return filmList;
    }

    @Override
    public List<Film> findByDateAfter(String dateAfter) {
        List<Film> filmList;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_DATE_AFTER_QUERY);
            preparedStatement.setDate(1, Date.valueOf(dateAfter));
            ResultSet resultSet = preparedStatement.executeQuery();

            filmList = collectFilm(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Troubles with connection  {}", e.getMessage());
            throw new SQLConnectionException(e.getMessage(), e.getCause());

        }
        return filmList;
    }

    @Override
    public List<Film> findByDirectorLastName(String directorLastName) {
        List<Film> filmList;
        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_DIRECTOR_LAST_NAME_QUERY);
            preparedStatement.setString(1, directorLastName + "%");
            ResultSet resultSet = preparedStatement.executeQuery();


            filmList = collectFilm(resultSet);

        } catch (SQLException e) {
            LOGGER.error("Troubles with connection  {}", e.getMessage());
            throw new SQLConnectionException(e.getMessage(), e.getCause());

        }
        return filmList;
    }

    @Override
    public List<Film> findByDirectorLastNameAndPeriodOfDate(String directorLastName, String dateAfter, String dateBefore) {
        List<Film> filmList ;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_DIRECTOR_LAST_NAME_AND_DATE_QUERY);
            preparedStatement.setString(1, directorLastName);
            preparedStatement.setDate(2, Date.valueOf(dateAfter));
            preparedStatement.setDate(3, Date.valueOf(dateBefore));
            ResultSet resultSet = preparedStatement.executeQuery();
            filmList = collectFilm(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Troubles with connection  {}", e.getMessage());
            throw new SQLConnectionException(e.getMessage(), e.getCause());

        }
        return filmList;
    }

    @Override
    public List<Film> findByDirectorLastNameAndDateAfter(String directorLastName, String dateAfter) {
        List<Film> filmList ;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_DIRECTOR_LAST_NAME_AND_DATE_AFTER_QUERY);
            preparedStatement.setString(1, directorLastName);
            preparedStatement.setDate(2, Date.valueOf(dateAfter));
            ResultSet resultSet = preparedStatement.executeQuery();
            filmList = collectFilm(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Troubles with connection  {}", e.getMessage());
            throw new SQLConnectionException(e.getMessage(), e.getCause());

        }
        return filmList;
    }

    @Override
    public List<Film> findByDirectorLastNameAndDateBefore(String directorLastName, String dateBefore) {
        List<Film> filmList ;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_DIRECTOR_LAST_NAME_AND_DATE_BEFORE_QUERY);
            preparedStatement.setString(1, directorLastName);
            preparedStatement.setDate(2, Date.valueOf(dateBefore));
            ResultSet resultSet = preparedStatement.executeQuery();
            filmList = collectFilm(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Troubles with connection  {}", e.getMessage());
            throw new SQLConnectionException(e.getMessage(), e.getCause());

        }
        return filmList;
    }

    private  List<Film> collectFilm(ResultSet resultSet) throws SQLException {
        List<Film> filmList = new ArrayList<>();

        while (resultSet.next()) {
            Film film = new Film();
            Director director = new Director();

            film.setName(resultSet.getString("name"));
            film.setGenre(resultSet.getString("genre"));
            film.setReleaseDate(resultSet.getDate("release_date").toLocalDate());

            director.setFirstName(resultSet.getString("first_name"));
            director.setLastName(resultSet.getString("last_name"));
            director.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
            film.setDirector(director);
            filmList.add(film);
        }

        return filmList;
    }


}
