package api;
import org.apache.commons.lang3.RandomStringUtils;

public class CreateUserRequest {
    public String email;
    public String password;
    public String name;
    public static final String EMAIL_POSTFIX = "@yandex.ru";


    public CreateUserRequest() {
    }

    public CreateUserRequest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public CreateUserRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public CreateUserRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public CreateUserRequest setName(String name) {
        this.name = name;
        return this;
    }

    public static CreateUserRequest generateRandomUser() {
        final String email = RandomStringUtils.randomAlphabetic(10) + EMAIL_POSTFIX;
        final String password = RandomStringUtils.randomAlphabetic(10);
        final String name = RandomStringUtils.randomAlphabetic(10);
        return new CreateUserRequest(email, password, name);
    }



}
