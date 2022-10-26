package com.progobot.practicespringboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Request;
import com.ning.http.client.RequestBuilder;
import com.ning.http.client.Response;
import com.ning.http.client.generators.FileBodyGenerator;
import com.ning.http.client.multipart.FilePart;
import com.ning.http.client.multipart.MultipartBody;
import jdk.jfr.DataAmount;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

import static java.nio.charset.StandardCharsets.UTF_8;

@Data
class FileBody1{
	File myFile;
}
@SpringBootApplication
public class PracticespringbootApplication {

	public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
		File file = new File("D:\\Projects\\ADF Training\\Bank-App-SpringBoot\\src\\main\\resources\\ste.txt");
		FileBody1 fileBody = new FileBody1();
		fileBody.setMyFile(file);
		RequestBuilder requestBuilder = new RequestBuilder(HttpMethod.POST.name());
		/*Request request = requestBuilder.setUrl("http://localhost:3000/uploadFile").addBodyPart(new FilePart("myFile", file,
				"text/plain", UTF_8)).build();*/
		Request request = requestBuilder.setUrl("http://localhost:3000/uploadFile").addBodyPart(new FilePart("file", file, "false", UTF_8)).build();
		System.out.println("cels "+request.getBodyEncoding());
		AsyncHttpClient httpClient = new AsyncHttpClient();
		Response response = httpClient.executeRequest(request).get();
		System.out.println(response.getResponseBody());
	}

}
