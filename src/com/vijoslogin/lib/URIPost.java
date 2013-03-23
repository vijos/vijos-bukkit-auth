package com.vijoslogin.lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class URIPost {

	public URIPost() {
		
		//Trust all certificates
		
		try {
			// get ssl context
			SSLContext sc = SSLContext.getInstance("SSL");

			// Create empty HostnameVerifier
			HostnameVerifier hv = new HostnameVerifier() {
				public boolean verify(String urlHostName, SSLSession session) {
					return true;
				}
			};

			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(
						java.security.cert.X509Certificate[] certs,
						String authType) {
				}

				public void checkServerTrusted(
						java.security.cert.X509Certificate[] certs,
						String authType) {
				}
			} };

			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			SSLSocketFactory sslSocketFactory = sc.getSocketFactory();

			HttpsURLConnection.setDefaultSSLSocketFactory(sslSocketFactory);
			HttpsURLConnection.setDefaultHostnameVerifier(hv);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getPost(String postUrl, String stringBuffer) throws IOException {
		
		URL url = new URL(postUrl);
		URLConnection uc = url.openConnection();
		HttpsURLConnection connection = (HttpsURLConnection) uc;

		connection.setDoOutput(true);

		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");

		outputStreamWriter.write(stringBuffer);

		outputStreamWriter.flush();
		outputStreamWriter.close();

		String sCurrentLine = "";
		String sTotalString = "";

		InputStream inputStream = connection.getInputStream();

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

		while ((sCurrentLine = bufferedReader.readLine()) != null) {
			if (sTotalString.equals(""))
				sTotalString = sCurrentLine;
			else
				sTotalString += "\r\n" + sCurrentLine;
		}

		sTotalString = URLDecoder.decode(URLDecoder.decode(sTotalString, "UTF-8"), "UTF-8");

		return sTotalString;
	}
}