package com.test.utilities;

import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;

public class CharacterTestBase{

    protected Logger log = LogManager.getLogger();

    @BeforeAll
    public static void init(){

        RestAssured.baseURI="https://rickandmortyapi.com";

    }


}
