package entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Session {
    private int id;
    private LocalDate date;
    private LocalTime time;
    private Film film;

    public Session(LocalDate date, LocalTime time, Film film) {
        this.date = date;
        this.time = time;
        this.film = film;
    }

    public Session(int id, LocalDate date, LocalTime time, Film film) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.film = film;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return id == session.id && Objects.equals(date, session.date) && Objects.equals(time, session.time) && Objects.equals(film, session.film);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, time, film);
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", date=" + date +
                ", time=" + time +
                ", film=" + film +
                '}';
    }
}
