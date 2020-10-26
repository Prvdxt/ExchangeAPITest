package baseclass;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class RestAPIExecution {

	ResponseBody r;

	public Response restCheck(String url, String method) {

		RestAssured.baseURI = url;
		RequestSpecification request = RestAssured.given();
		Response response = request.request(Method.valueOf(method), RestAssured.baseURI);
		return response;

	}

	public int checkStatus(Response response1) {
		int status = response1.getStatusCode();
		return status;
	}

	public String checkResponseBody(Response response1) {
		r = response1.getBody();
		String body = r.asString();
		return body;

	}

	public String checkStatusDate(Response response1) {

		r = response1.getBody();
		String body = r.asString();
		return body;

	}
}
