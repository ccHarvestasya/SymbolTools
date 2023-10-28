package com.harvestasya.tools.symbol.util;

import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class CertExpiration {

	public static void main(String[] args) {
		try {
			CertExpiration sn = new CertExpiration();
			//			sn.printCertExpiration("dhealth03.harvestasya.com");
			Date d = sn.getCertExpiration("dhealth02.harvestasya.com", 7900);
			System.out.println(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Date getCertExpiration(String host, Integer port) throws Exception {
		SSLContext context = SSLContext.getInstance("TLS");
		TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		tmf.init((KeyStore) null);
		X509TrustManager defaultTrustManager = (X509TrustManager) tmf.getTrustManagers()[0];
		SavingTrustManager tm = new SavingTrustManager(defaultTrustManager);
		context.init(null, new TrustManager[] { tm }, null);
		SSLSocketFactory factory = context.getSocketFactory();
		SSLSocket socket = (SSLSocket) factory.createSocket(host, port);
		socket.setSoTimeout(2000);

		Date notAfter = null;
		try {
			socket.startHandshake();

			X509Certificate[] chain = tm.chain;
			X509Certificate cert = chain[0];

			notAfter = cert.getNotAfter();
		} catch (SSLException e) {
			e.printStackTrace();
		} finally {
			socket.close();
		}

		return notAfter;
	}

	private static class SavingTrustManager implements X509TrustManager {
		private X509Certificate[] chain;

		SavingTrustManager(X509TrustManager tm) {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[0];
		}

		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			throw new UnsupportedOperationException();
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			this.chain = chain;
		}
	}
}
