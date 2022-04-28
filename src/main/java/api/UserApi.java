package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UserApi extends Base {
    @Step("Create new user")
    public Response createUser(CreateUserRequest createUserRequest) {
        return given()
                .spec(getBaseSpec())
                .body(createUserRequest)
                .when()
                .post(EndpointsUserApi.POST_REGISTER_CUSTOMER);
    }

    @Step("Get accessToken user")
    public String getAccessToken(Response response) {
        String token = response.then()
                .extract()
                .path("accessToken");

        return token.substring(7);
    }

    @Step("Delete user")
    public void deleteCustomer(String token) {
        if (token == null) {
            return;
        }
        given()
                .spec(getBaseSpec())
                .auth().oauth2(token)
                .when()
                .delete(EndpointsUserApi.DELETE_CUSTOMER).then()
                .statusCode(202);
    }

}
