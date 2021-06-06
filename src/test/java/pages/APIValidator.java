package pages;

import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.pages.PageObject;
import org.junit.Assert;


import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class APIValidator extends PageObject {
    private static final String url = "http://api.openweathermap.org/data/3.0/stations";
    private static final String apiKey = "dc18d5a5373057438b46cd2b15c3279b";

//Register without apiKey
    public void registerStationwithoutAPI(DataTable dataTable) {
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        given()
                .header("key", "")
                .header("Content-Type", "application/json")
                .param("external_id", list.get(0).get("external_id"))
                .param("name", list.get(0).get("name"))
                .param("latitude", list.get(0).get("latitude"))
                .param("longitude", list.get(0).get("longitude"))
                .param("altitude", list.get(0).get("altitude"))
                .post("/stations")
                .then().statusCode(Integer.parseInt(list.get(0).get("status code"))).statusLine(list.get(0).get("status line"));
    }

    //validate api access
    public void validateAPIaccess() {
        RestAssured.baseURI = url;
        given().contentType(ContentType.JSON).get(url + "?APPID=" + apiKey).then().statusCode(200);

    }

    //Register two stations
    public void postTwoStations(String externalID, String name, String latitude, String longitude, String altitude, String statuscode) {
        given()
                .header("Content-Type", "application/json")
                .param("external_id", externalID)
                .param("name", name)
                .param("latitude", latitude)
                .param("longitude", longitude)
                .param("altitude", altitude)
                .post(url + "?APPID=" + apiKey).then().statusCode(Integer.parseInt(statuscode));
    }

    //validate created stations
    public void getStationDetails(String externalID) {
        RestAssured.baseURI = url;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get(url + "?APPID=" + apiKey);
        String body = response.getBody().toString();
        Assert.assertTrue(body.contains(externalID));
    }
}

