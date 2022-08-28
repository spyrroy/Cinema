package entity;

import java.util.Objects;

public class Film {
    private int id;
    private String name;
    private String description;
    private int duration;
    private Genre genre;

    public Film(String name, String description, int duration, Genre genre) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.genre = genre;
    }

    public Film(int id, String name, String description, int duration, Genre genre) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return id == film.id && duration == film.duration && Objects.equals(name, film.name) && Objects.equals(description, film.description) && Objects.equals(genre, film.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, duration, genre);
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", genre=" + genre +
                '}';
    }
}
