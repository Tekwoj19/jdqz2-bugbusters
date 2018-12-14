package com.infoshare.bug_busters.api;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;


import static io.restassured.RestAssured.given;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;


public class Catalogue {

        @Test
        public void checkingCatalogue(){
            given()
                    .contentType("application/json").
                    when()
                    .get("http://localhost:4180/cart").
                    then()
                    .log().all().statusCode(200);
        }

    @Test
    public void makeSureThatJasonPlaceHolderIsUp() {
        given().when().get("http://localhost:4180").then().statusCode(200);

    }

    @Test
    public void checkingCatalogueSizeResponse() {

        RestAssured.registerParser("text/plain", Parser.JSON);
        given().
                when().
                get("http://localhost:4180/catalogue/size").
                then().
                statusCode(200).log().all().
                body("size", equalTo(9));
    }

    @Test
    public void testingProductResponseById() {
        RestAssured.registerParser("text/plain", Parser.JSON);
        given().
                when().
                get("http://localhost:4180/catalogue/03fef6ac-1896-4ce8-bd69-b798f85c6e0b").
                then().
                assertThat().
                statusCode(200).log().all().
                body("name", equalTo("Holy"));

    }

    @Test
    public void testingProductResponseByIdNotFound() {
        RestAssured.registerParser("text/plain", Parser.JSON);
        given().
                when().
                get("http://localhost:4180/catalogue/sdfsdfsd").
                then().
                assertThat().
                statusCode(200).log().all().
                body("error", equalTo("Do: not found"));

    }

    @Test
    public void testingTags() {
        RestAssured.registerParser("text/plain", Parser.JSON);
        List<String> tagi = Arrays.asList("brown", "geek", "formal", "blue", "skin", "red", "action", "sport", "black", "magic", "green");
        List<String> tagsFromResponse = given().
                when().
                get("http://localhost:4180/tags").
                then().
                assertThat().
                statusCode(200).log().all().
                body("tags", hasSize(11)).
                extract().jsonPath().getList("tags");
        Assert.assertEquals(tagi, tagsFromResponse);
    }


}

