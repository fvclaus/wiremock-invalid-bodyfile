package fvclaus;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.Notifier;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private boolean hasError = false;
    
    @Test
    public void shouldAnswerWithTrue()
    {

        Notifier notifier = new Notifier() {

            @Override
            public void info(String message) {
                
            }

            @Override
            public void error(String message) {
                hasError = true;
                
            }

            @Override
            public void error(String message, Throwable t) {
                hasError = true;
                
            }
            
        };

        WireMockServer server = new WireMockServer(new WireMockConfiguration().port(4646).notifier(notifier));

        server.start();
        server.stubFor(get("/hello").willReturn(aResponse().withBodyFile("hello")));
        
        try {
            URL url = new URL("http://localhost:4646/hello");
            URLConnection c = url.openConnection();
            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(
                                    c.getInputStream()));
            String inputLine;
    
            while ((inputLine = in.readLine()) != null) 
                System.out.println(inputLine);
            in.close();
        } catch (IOException e) {

        }

        assertThat(hasError, equalTo(true));
    }
}
