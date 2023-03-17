package ru.netology.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;

import static io.restassured.RestAssured.given;


public class RestAPITest {

    private RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(8080)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();


    @Test
    public void getSuccessResponseIfPayWithApprovedStatusCard() {
        given()
                .spec(requestSpec)
                .baseUri("http://localhost:8080/api/v1")
                .body(DataHelper.successPaymentWithApprovedCard())
                .when()
                .post("/pay")
                .then()
                .statusCode(200);
    }

    @Test
    public void getErrorResponseIfPayWithDeclinedStatusCard() {
        given()
                .spec(requestSpec)
                .baseUri("http://localhost:8080/api/v1")
                .body(DataHelper.errorNotificationWhilePayingWithDeclinedCard())
                .when()
                .post("/pay")
                .then()
                .statusCode(400);
    }

    @Test

    public void getSuccessResponseIfPayWithApprovedStatusCreditCard() {
        given()
                .spec(requestSpec)
                .baseUri("http://localhost:8080/api/v1")
                .body(DataHelper.successPaymentWithApprovedCard())
                .when()
                .post("/credit")
                .then()
                .statusCode(200);
    }

    @Test

    public void getErrorResponseIfPayWithDeclinedStatusCreditCard() {
        given()
                .spec(requestSpec)
                .baseUri("http://localhost:8080/api/v1")
                .body(DataHelper.errorNotificationWhilePayingWithDeclinedCard())
                .when()
                .post("/credit")
                .then()
                .statusCode(400);
    }

}


