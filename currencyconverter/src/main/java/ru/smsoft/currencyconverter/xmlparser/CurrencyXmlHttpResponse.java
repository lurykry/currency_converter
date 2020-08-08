package ru.smsoft.currencyconverter.xmlparser;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public class CurrencyXmlHttpResponse {

    private static final String URL = "http://www.cbr.ru/scripts/XML_daily.asp";

    public String getCurrencyXml() throws URISyntaxException, IOException, InterruptedException, ExecutionException {
        HttpRequest request = setUpHttpRequest();
        HttpResponse<String> response = setUpHttpResponse(request);
        return response.body();
    }

    private HttpRequest setUpHttpRequest() throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri(new URI(URL))
                .header("Content-Type", "application/xml")
                .GET()
                .build();
    }
    private HttpResponse<String> setUpHttpResponse(HttpRequest request) throws IOException, InterruptedException, ExecutionException {
        CompletableFuture<HttpResponse<String>> future = HttpClient
                .newBuilder()
                .build()
                .sendAsync(request,  HttpResponse.BodyHandlers.ofString());
        return future.get();
    }
}
