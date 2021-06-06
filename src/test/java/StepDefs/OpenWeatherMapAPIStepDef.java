package StepDefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.thucydides.core.steps.ScenarioSteps;
import pages.APIValidator;

public class OpenWeatherMapAPIStepDef extends ScenarioSteps {
    APIValidator apiValidator;

    @Given("^user registers a new station with below details$")
    public void userRegistersANewStationWithBelowDetails(DataTable dataTable) {
        apiValidator.registerStationwithoutAPI(dataTable);
    }

    @Given("user is able to access the API")
    public void userIsAbleToAccessTheAPI() {
        apiValidator.validateAPIaccess();

    }


    @Given("user registers a new station with these details {string}, {string},{string},{string},{string},{string}")
    public void userRegistersANewStationWithTheseDetails(String externalID, String name, String latitude, String longitude, String altitude, String statuscode) {
        apiValidator.postTwoStations(externalID, name, latitude, longitude, altitude, statuscode);
    }

    @Then("user requests the station details and validate the registered stations are available with {string}")
    public void userRequestsTheStationDetailsAndValidateTheRegisteredStationsAreAvailable(String id) {
        apiValidator.getStationDetails(id);
    }
}
