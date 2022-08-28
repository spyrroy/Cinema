package com.my.command;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public abstract class Command {
    protected Command() {

    }
    public abstract String doCommand(HttpServletRequest req, HttpServletResponse resp) throws CommandException;

    @Override
    public String toString() {
        return "Command=[" + getClass().getSimpleName() + "]";
    }
}
