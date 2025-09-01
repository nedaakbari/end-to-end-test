package org.example.sample4;

import io.qameta.allure.Allure;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.nio.charset.StandardCharsets;

public class AllureRestAssuredFilter implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {

        // اجرای request
        Response response = ctx.next(requestSpec, responseSpec);

        // ------------------- Request -------------------
        StringBuilder requestBuilder = new StringBuilder();
        requestBuilder.append(requestSpec.getMethod())
                .append(" ")
                .append(requestSpec.getURI())
                .append("\n\n");

        // Headers
        requestBuilder.append("Headers:\n");
        requestSpec.getHeaders().asList().forEach(
                h -> requestBuilder.append(h.getName()).append(": ").append(h.getValue()).append("\n")
        );

        // Body
        requestBuilder.append("\nBody:\n")
                .append(requestSpec.getBody() == null ? "No body" : requestSpec.getBody());

        Allure.addAttachment("HTTP Request", "text/plain",
                requestBuilder.toString(), StandardCharsets.UTF_8.toString());

        // ------------------- Response -------------------
        StringBuilder responseBuilder = new StringBuilder();
        responseBuilder.append("Status code: ").append(response.getStatusCode()).append("\n\n");

        // Headers
        responseBuilder.append("Headers:\n");
        response.getHeaders().asList().forEach(
                h -> responseBuilder.append(h.getName()).append(": ").append(h.getValue()).append("\n")
        );

        // Body
        responseBuilder.append("\nBody:\n")
                .append(response.getBody().asPrettyString());

        Allure.addAttachment("HTTP Response", "text/plain",
                responseBuilder.toString(), StandardCharsets.UTF_8.toString());

        return response;
    }
}
