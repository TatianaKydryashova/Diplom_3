package api;
import org.apache.commons.lang3.RandomStringUtils;

public class CreateUserRequest {
    private String email;
    private String password;
    private String name;



    public CreateUserRequest() {
    }

    public CreateUserRequest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static CreateUserRequest generateRandomUser() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setEmail(RandomStringUtils.randomAlphabetic(10) + "@yandex.ru");
        createUserRequest.setPassword(RandomStringUtils.randomAlphabetic(10));
        createUserRequest.setName(RandomStringUtils.randomAlphabetic(10));
        return createUserRequest;
    }



}
