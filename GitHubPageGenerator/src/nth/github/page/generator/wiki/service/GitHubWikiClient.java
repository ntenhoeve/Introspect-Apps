package nth.github.page.generator.wiki.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
@Deprecated
public class GitHubWikiClient {

	private static final String GIT_HUB_LOGIN_URI = "https://github.com/login?return_to=%2Flogin";
	private static final String GIT_HUB_URI = "https://github.com";

	public static void main(String[] args) throws Exception {
		BasicCookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient httpclient = HttpClients.custom()
				.setDefaultCookieStore(cookieStore).build();

		try {
			HttpGet httpget = new HttpGet(GIT_HUB_LOGIN_URI);

			String authentificationCode = getAuthentificationCodeFromLoginPage(
					cookieStore, httpclient, httpget);

			login(cookieStore, httpclient, authentificationCode);

			getWikiPages(
					"https://github.com/ntenhoeve/Introspect-Framework/wiki/_pages",
					httpclient);

			// removeWikiPages()

		} finally {
			httpclient.close();
		}
	}

	private static void getWikiPages(String gitHubWikiPagesUri,
			CloseableHttpClient httpclient) throws ClientProtocolException,
			IOException, URISyntaxException {
		HttpUriRequest gitHubWikiPagesRequest = RequestBuilder.post()
				.setUri(new URI(gitHubWikiPagesUri)).build();
		CloseableHttpResponse response = httpclient
				.execute(gitHubWikiPagesRequest);

		try {
			HttpEntity entity = response.getEntity();

			System.out.println("Git Hub Wiki Pages: "
					+ response.getStatusLine());

			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));

			String line = "";
			while ((line = rd.readLine()) != null) {
				// System.out.println(line);
			}

			// EntityUtils.consume(entity);
			//
			// System.out.println("Post logon cookies:");
			// List<Cookie> cookies = cookieStore.getCookies();
			// if (cookies.isEmpty()) {
			// System.out.println("None");
			// } else {
			// for (int i = 0; i < cookies.size(); i++) {
			// System.out.println("- " + cookies.get(i).toString());
			// }
			// }
		} finally {
			response.close();
		}
	}

	private static void login(BasicCookieStore cookieStore,
			CloseableHttpClient httpclient, String authentificationCode)
			throws URISyntaxException, IOException, ClientProtocolException {
		HttpUriRequest gitHubLoginRequest = RequestBuilder.post()
				.setUri(new URI(GIT_HUB_LOGIN_URI))
				.addParameter("authenticity_token", authentificationCode)
				.addParameter("login", "ntenhoeve")
				.addParameter("password", "GithuB66^")
				.addParameter("commit", "Sign in").build();
		CloseableHttpResponse response = httpclient.execute(gitHubLoginRequest);

		try {
			HttpEntity entity = response.getEntity();

			System.out.println("Login form get: " + response.getStatusLine());
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));

			String line = "";
			while ((line = rd.readLine()) != null) {
				 System.out.println(line);
			}
			EntityUtils.consume(entity);

			System.out.println("Post logon cookies:");
			List<Cookie> cookies = cookieStore.getCookies();
			if (cookies.isEmpty()) {
				System.out.println("None");
			} else {
				for (int i = 0; i < cookies.size(); i++) {
					System.out.println("- " + cookies.get(i).toString());
				}
			}
		} finally {
			response.close();
		}
	}

	private static String getAuthentificationCodeFromLoginPage(
			BasicCookieStore cookieStore, CloseableHttpClient httpclient,
			HttpGet httpget) throws IOException, ClientProtocolException {
		CloseableHttpResponse response = httpclient.execute(httpget);
		String authentificationCode = null;
		try {
			HttpEntity entity = response.getEntity();

			System.out.println("Login form get: " + response.getStatusLine());

			BufferedReader rd = new BufferedReader(new InputStreamReader(
					entity.getContent()));

			String line = "";
			while ((line = rd.readLine()) != null) {
				if (line.contains("csrf-token")) {
					System.out.println(line);
					int begin = line.indexOf("\"") + 1;
					int end = line.indexOf("\"", begin);
					authentificationCode = line.substring(begin, end);
				}
			}
			if (authentificationCode == null) {
				throw new RuntimeException(
						"Could not find csrf authentification code on"
								+ GIT_HUB_LOGIN_URI);
			}

			EntityUtils.consume(entity);

			System.out.println("Initial set of cookies:");
			List<Cookie> cookies = cookieStore.getCookies();
			if (cookies.isEmpty()) {
				System.out.println("None");
			} else {
				for (int i = 0; i < cookies.size(); i++) {
					System.out.println("- " + cookies.get(i).toString());
				}
			}
		} finally {
			response.close();
		}
		return authentificationCode;
	}
}
