package dev.infochem.clilibrary;

public interface Parser {
    CommandContainer getCommands();
    Action[] parse(String[] args);
}
