package com.porter.web.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.MultipartStream;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.http.converter.xml.XmlAwareFormHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.RequestToViewNameTranslator;

//@Ignore
public class PartControllerIntegrateTest {

	private static final String LOCALHOST = "http://localhost:8080/porter/";
	private static final String CLOUD = "http://117.52.91.145:8080/porter/";
	private static final String DEFAULT = CLOUD;
	
	private List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
	@Test
	public void get_성공() {
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response  = restTemplate.getForEntity(CLOUD+"articles/4", String.class);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		assertThat(response.getStatusCode().value(), is(200));
	}
	
	@Test
	public void create_성공2() {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(DEFAULT+"articles");
		MultipartEntity entity = new MultipartEntity();
		try {
			entity.addPart("apiKey", new StringBody("test@naver.com-fQeOUqV809Ym8gqOw1OR1bggibZdtgbUM+Bn8TSffE8=", Charset.forName("UTF-8")));
			entity.addPart("name", new StringBody("나사", Charset.forName("UTF-8")));
			entity.addPart("middleCategory", new StringBody("9000", Charset.forName("UTF-8")));
			entity.addPart("largeCategory", new StringBody("9001", Charset.forName("UTF-8")));
			entity.addPart("keywords", new StringBody("키워드2", Charset.forName("UTF-8")));
			entity.addPart("keywords", new StringBody("키워드2", Charset.forName("UTF-8")));
			entity.addPart("price", new StringBody("3000", Charset.forName("UTF-8")));
			entity.addPart("maker", new StringBody("현대", Charset.forName("UTF-8")));
			entity.addPart("state", new StringBody("상태좋음", Charset.forName("UTF-8")));
			entity.addPart("quantity", new StringBody("100", Charset.forName("UTF-8")));
			entity.addPart("description", new StringBody("상세설명입니다.", Charset.forName("UTF-8")));
			entity.addPart("region", new StringBody("1", Charset.forName("UTF-8")));
			entity.addPart("imageFiles", new FileBody(new File("src\\test\\java\\res\\test.jpg")));
			entity.addPart("imageFiles", new FileBody(new File("src\\test\\java\\res\\test.jpg")));
			post.setEntity(entity);
			HttpResponse response = httpClient.execute(post);
			String result = EntityUtils.toString(response.getEntity());
			
			System.out.println(result);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		post.setEntity(entity);
	}
	
	@Test
	public void create_성공3() {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(DEFAULT+"parts");
		MultipartEntity entity = new MultipartEntity();
		try {
			entity.addPart("apiKey", new StringBody("kd980311@naver.com-1394117298290-XlfbjqU+e1jdZlf1wM+30kO2PPZvq/rMC6MkibxTjjU=", Charset.forName("UTF-8")));
			entity.addPart("name", new StringBody("나사", Charset.forName("UTF-8")));
			entity.addPart("middleCategory", new StringBody("9000", Charset.forName("UTF-8")));
			entity.addPart("largeCategory", new StringBody("9001", Charset.forName("UTF-8")));
			entity.addPart("keywords", new StringBody("키워드2", Charset.forName("UTF-8")));
			entity.addPart("keywords", new StringBody("키워드2", Charset.forName("UTF-8")));
			entity.addPart("price", new StringBody("3000", Charset.forName("UTF-8")));
			entity.addPart("maker", new StringBody("현대", Charset.forName("UTF-8")));
			entity.addPart("state", new StringBody("상태좋음", Charset.forName("UTF-8")));
			entity.addPart("quantity", new StringBody("100", Charset.forName("UTF-8")));
			entity.addPart("description", new StringBody("상세설명입니다.", Charset.forName("UTF-8")));
			entity.addPart("region", new StringBody("1", Charset.forName("UTF-8")));
			entity.addPart("imageFiles", new FileBody(new File("src\\test\\java\\res\\한글테스트.jpg")));
			entity.addPart("imageFiles", new FileBody(new File("src\\test\\java\\res\\한글테스트.jpg")));
			post.setEntity(entity);
			HttpResponse response = httpClient.execute(post);
			String result = EntityUtils.toString(response.getEntity());
			
			System.out.println(result);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		post.setEntity(entity);
	}
	
	@Test
	public void aa(){
		RestTemplate rest = new RestTemplate();
		ResponseEntity<byte[]> response = rest.getForEntity("http://localhost:8080/porter/images/C806E9585A7B4DCD8441EBF0512BC98A.jpg", byte[].class);
		System.out.println(response.getBody());
		System.out.println(response.getStatusCode());
	}
	
}
