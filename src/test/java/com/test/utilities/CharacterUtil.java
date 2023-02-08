package com.test.utilities;

import com.test.pojo.Character;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CharacterUtil {


    public static Character createCharacter() {
        Character character=new Character();
        Faker faker=new Faker();
        character.setName(faker.name().firstName());
        //"1234545363463"                  method is giving me the String then I need to convert into te long
        character.setPhone(Long.parseLong(faker.numerify("###########")));

        int i = faker.number().numberBetween(0, 1);
        if (i==0){
            character.setGender("Female");
        }else {
            character.setGender("Male");
        }

        return character;
    }

    public static Response postCharacter(Character character) {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(character)
                .when()
                .post("/api/character");
        return response;


    }

    public static Response getCharacter(int createdCharacterId) {
        Response response = given()
                .accept(ContentType.JSON)
                //   .contentType(ContentType.JSON)
                .pathParam("id",createdCharacterId)
                .when()
                .get("/api/character/{id}");
        return response;

    }

    public static Response updateCharacter(int createdCharacterId, Character updatedCharacter) {

        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("id",createdCharacterId)
                .body(updatedCharacter)
                .when()
                .put("/api/character/{id}");
        return response;



    }

    public static Response deleteCharacter(int createdCharacterId) {
        Response response = given()
                // .accept(ContentType.JSON)
                //   .contentType(ContentType.JSON)
                .pathParam("id",createdCharacterId)
                .when()
                .delete("/api/character/{id}");
        return response;

    }
}