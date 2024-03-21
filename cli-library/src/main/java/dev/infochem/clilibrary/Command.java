package dev.infochem.clilibrary;

public interface Command {
    Project getProject();
    Action getAction(Object... params);
    String getCaption();
    String getMask();
}
