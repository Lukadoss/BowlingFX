package es.ulpgc.bowling.config;

/**
 * Enum representing colors used in application
 * @author Petr Lukasik
 */
public enum Color {
    RED("#FF3333"),
    BLUE("#3333FF"),
    YELLOW("#FFD700"),
    GREEN("#00CC00"),
    PURPLE("#FF33FF"),
    ORANGE("#FF9933"),
    PINK("#FF66B2"),
    GRAY("#C0C0C0");

    private final String color;

    Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
