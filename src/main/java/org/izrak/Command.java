package org.izrak;

import org.izrak.exception.command.CommandException;

public interface Command {
    String executeCommands(String commands) throws CommandException;
}
