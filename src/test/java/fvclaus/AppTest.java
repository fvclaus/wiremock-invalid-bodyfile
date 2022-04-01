package fvclaus;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws Exception
    {
        WireMockServer server = new WireMockServer(new WireMockConfiguration().port(4646).notifier(new ConsoleNotifier(true)));
        server.start();
        server.stubFor(get("/hello").willReturn(aResponse().withBodyFile("hello")));
        
        URL url = new URL("http://localhost:4646/hello");
        URLConnection c = url.openConnection();
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                c.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) 
            System.out.println(inputLine);
        in.close();
    }
}
