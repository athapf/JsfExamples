package de.thaso.jd.web.it.glassfish;

import de.thaso.jd.web.it.base.ApplicationServerBase;
import de.thaso.jd.web.it.base.PropertiesManager;
import org.glassfish.embeddable.BootstrapProperties;
import org.glassfish.embeddable.Deployer;
import org.glassfish.embeddable.GlassFish;
import org.glassfish.embeddable.GlassFishException;
import org.glassfish.embeddable.GlassFishProperties;
import org.glassfish.embeddable.GlassFishRuntime;

import java.io.File;
import java.util.Properties;

/**
 * GlassfishEmbeddedServer
 *
 * @author thaler
 * @since 29.09.16
 */
public class GlassfishEmbeddedServer implements ApplicationServerBase {

    private static Properties properties;
    private static GlassFish glassfish;

    @Override
    public void startEmbeddedServer() throws Exception {
        System.out.println("Opening the container");
        properties = PropertiesManager.readDevelopProperties();
        final BootstrapProperties bootstrapProperties = new BootstrapProperties();
        final GlassFishProperties glassFishProperties = new GlassFishProperties();
        glassFishProperties.setPort("http-listener", Integer.parseInt(properties.getProperty("app.server.http.port")));
        glassfish = GlassFishRuntime.bootstrap(bootstrapProperties).newGlassFish(glassFishProperties);
        glassfish.start();

        deployApp();
    }

    private void deployApp() throws GlassFishException {
        File war = new File("./target/war");
        Deployer deployer = glassfish.getDeployer();
        deployer.deploy(war, "--contextroot=" + properties.getProperty("app.server.contextroot"), "--force=true");
    }

    private String createAppUrl() {
        final StringBuilder builder = new StringBuilder();
        builder.append("http://localhost:")
                .append(properties.getProperty("app.server.http.port"))
                .append('/')
                .append(properties.getProperty("app.server.contextroot"));
        return builder.toString();
    }

    @Override
    public void stopEmbeddedServer() throws Exception {
        glassfish.stop();
        System.out.println("Closing the container");
    }

    @Override
    public String getApplicationUrl() {
        return createAppUrl();
    }
}
