import com.github.tomakehurst.wiremock.WireMockServer;
        import org.junit.AfterClass;
        import org.junit.BeforeClass;

public class AbstractWireMockConfig {

    static WireMockServer wireMockServer;

    @BeforeClass
    public static void setup() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();
    }

    @AfterClass
    public static void tearDown() {
        wireMockServer.stop();
    }
}
