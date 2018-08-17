import model.User;
import com.github.nilankamanoj.Validator;
import org.junit.jupiter.api.Test;
import java.util.Map;

public class ValidatorTest {
    @Test
    public void test() {
        User user = new User(20, "fxxsx", "male", "pass", "passs", "aaaa@aa.com", "https://aaaa.com");
        Validator validator = new Validator(
                "https://cors.io/?http://nilankamanoj.tk/universal-validator/validation.json");
        Map out = validator.validate(user, "formRegister");
        System.out.println(out);
    }

}
