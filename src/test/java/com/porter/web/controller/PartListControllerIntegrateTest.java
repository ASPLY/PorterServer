package com.porter.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class PartListControllerIntegrateTest {

	private static final String LOCALHOST = "127.0.0.1";
	private static final String CLOUD = "117.52.91.145";
	private static final String DEFAULT = CLOUD;

	@Test // 톰캣 UTF-8 지원하도록 변경해야해
	public void search_성공() {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet get;
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("keyword", "나"));
		params.add(new BasicNameValuePair("largeCategory", "1000"));
		params.add(new BasicNameValuePair("middleCategory", "1002"));
		params.add(new BasicNameValuePair("count", "2"));
		try {
			String query = URLEncodedUtils.format(params, "UTF-8");
			System.out.println(query);
			URI uri = URIUtils.createURI("http", DEFAULT, 8080, "/porter/partLists", query, null);
			System.out.println(uri.toString());
			get = new HttpGet(uri);
			HttpResponse response = httpClient.execute(get);
			String result = EntityUtils.toString(response.getEntity());
			
			System.out.println(result);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (URISyntaxException e2) {
			e2.printStackTrace();
		}
		catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

