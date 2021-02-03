package domain;

import java.time.LocalDate;

public class Film {

    private Long directorId;
    private String name;
    private String genre;
    private LocalDate releaseDate;
    private Director director;

    public Film() {
    }

    public Film(Long directorId, String name, String genre, LocalDate releaseDate) {
        this.directorId = directorId;
        this.name = name;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return director.getFirstName() + " " + director.getLastName() + " " + director.getBirthDate() + " " + name + " " + genre + " " + releaseDate + " ";
    }
}

