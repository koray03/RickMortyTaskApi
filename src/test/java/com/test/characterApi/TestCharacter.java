package com.test.characterApi;

import com.test.utilities.CharacterTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
public class TestCharacter extends CharacterTestBase {


    @DisplayName("Get All Characters")
    @Test
    public void getAllCharacters(){


                 given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/character")
                .prettyPrint();
    }
    @DisplayName("Get All 'Alive' Characters and perform assertion with status code 200")
    @Test
    public void testAlive(){

        Response response = given().contentType(ContentType.JSON)
                .queryParam("status","Alive")
                .when().get("/api/character")
                .then().statusCode(200).extract().response();

   // Comparing Expected result 'Alive' Characters with status code 200 in json format with Actual response.

        assertEquals(200, response.statusCode());
        assertEquals("application/json; charset=utf-8", response.contentType());
        assertTrue(response.body().asString().contains("Alive"));
        response.prettyPrint();


    }
    @DisplayName("Get all 'Dead' and 'Alien' Characters and verify status code 200 in json format with Hamcrest" )
    @Test
    public void DeadAliens(){
        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("status","Dead")
                .and()
                .queryParam("species","Alien")
                .when().get("/api/character")
                .then().log().ifValidationFails()
                .assertThat().statusCode(200)
                .and()
                .assertThat().contentType("application/json; charset=utf-8").extract().response();

        //assertEquals(true,response.body().asString().contains("Dead"), "Expected  doesn't match with actual");

        assertTrue(response.body().asString().contains("Dead"));
        assertTrue(response.body().asString().contains("Alien"));



    }



}
