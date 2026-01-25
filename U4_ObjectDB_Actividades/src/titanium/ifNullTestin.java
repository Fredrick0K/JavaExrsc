package titanium;

import java.util.Objects;

public class ifNullTestin {

    public static void main(String[] args) {
        String name = "twn";
        String nameNoNull = Objects.requireNonNullElse(name, "Ye");
        System.out.println(nameNoNull);
    }
}