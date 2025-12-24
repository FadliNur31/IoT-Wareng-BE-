package com.example.demo.helper;

import com.example.demo.exceptionhandler.ResourceNotFound;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class SSLUtils {
    public static SSLSocketFactory getSocketFactory(String caCrtFile)
            throws Exception {

        CertificateFactory cf = CertificateFactory.getInstance("X.509");

        InputStream caInput = SSLUtils.class
                .getClassLoader()
                .getResourceAsStream(caCrtFile);

        if (caInput == null) {
            throw new ResourceNotFound("CA certificate not found: " + caCrtFile);
        }

        Certificate ca;
        try {
            ca = cf.generateCertificate(caInput);
        } finally {
            caInput.close();
        }

        // Create KeyStore
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null, null);
        keyStore.setCertificateEntry("ca-certificate", ca);

        // Create TrustManager
        TrustManagerFactory tmf =
                TrustManagerFactory.getInstance(
                        TrustManagerFactory.getDefaultAlgorithm()
                );
        tmf.init(keyStore);

        // Create SSLContext
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tmf.getTrustManagers(), null);

        return sslContext.getSocketFactory();
    }
}
