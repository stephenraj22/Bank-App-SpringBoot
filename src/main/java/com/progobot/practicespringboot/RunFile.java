package com.progobot.practicespringboot;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Request;
import com.ning.http.client.RequestBuilder;
import com.ning.http.client.Response;
import com.ning.http.client.generators.FileBodyGenerator;
import org.springframework.http.HttpMethod;

import java.io.File;
import java.util.concurrent.ExecutionException;

public class RunFile {
    public static void main() throws ExecutionException, InterruptedException {
        RequestBuilder requestBuilder = new RequestBuilder(HttpMethod.POST.name());
        requestBuilder.addHeader("Content-Type", "application/json");
        Request request = (Request) requestBuilder.setUrl("localhost:8000/topic/dummy").setBody(new FileBodyGenerator(new File("ste")));
        AsyncHttpClient httpClient = new AsyncHttpClient();
        Response response = httpClient.executeRequest(request).get();
    }
}
