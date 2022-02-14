package sirchardash.piria.museumtour.components.random;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomString {

    private final Random random = new Random();

    public String generate(int targetStringLength) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 96; // letter 'Z'

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}
