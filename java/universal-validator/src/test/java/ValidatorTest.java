import model.User;
import org.universalvalidator.Validator;
import org.junit.jupiter.api.Test;

public class ValidatorTest {
    @Test
    public void test(){
        User user = new User(15,"nilanka","male");
        Validator validator = new Validator();
        validator.validate(user,"formRegister");
    }


}
