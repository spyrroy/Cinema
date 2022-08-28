package com.my.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Ticket {
    private int id;
    private User user;
    private Seat seat;
    private Session session;
    private LocalDateTime date;

    public Ticket(User user, Seat seat, Session session) {
        this.user = user;
        this.seat = seat;
        this.session = session;
    }

    public Ticket(User user, Seat seat, Session session, LocalDateTime date) {
        this.user = user;
        this.seat = seat;
        this.session = session;
        this.date = date;
    }

    public Ticket(int id, User user, Seat seat, Session session, LocalDateTime date) {
        this.id = id;
        this.user = user;
        this.seat = seat;
        this.session = session;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && Objects.equals(user, ticket.user) && Objects.equals(seat, ticket.seat) && Objects.equals(session, ticket.session) && Objects.equals(date, ticket.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, seat, session, date);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", user=" + user +
                ", seat=" + seat +
                ", session=" + session +
                ", date=" + date +
                '}';
    }
}
