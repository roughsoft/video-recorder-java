package com.automation.remarks.remote.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

import java.util.logging.Logger;

/**
 * Created by Serhii_Pirohov on 11.05.2016.
 */
public class GridTest {

    private static final Logger LOGGER = Logger.getLogger(GridTest.class.getName());

    @Before
    public void setUp() throws Exception {
        Selenide.open("");
        WebDriver webDriver = WebDriverRunner.getWebDriver();
        SessionId sessionId = ((RemoteWebDriver) webDriver).getSessionId();

        System.out.println(sessionId);


    }

    @Test
    public void testName() throws Exception {


        Configuration.remote = "http://localhost:4444/wd/hub";
        Selenide.open("http://google.com.ua");


        Thread.sleep(5000);
    }

    private void sendRecordingRequest(final String url) {
        CloseableHttpResponse response = null;

        try (final CloseableHttpClient client = HttpClientBuilder.create().build()) {
            final HttpGet get = new HttpGet(url);
            response = client.execute(get);
            LOGGER.info("Node response: " + response);
        } catch (Exception ex) {
            LOGGER.severe("Unable to send recording request to node: " + ex);
        } finally {
            HttpClientUtils.closeQuietly(response);
        }
    }
}