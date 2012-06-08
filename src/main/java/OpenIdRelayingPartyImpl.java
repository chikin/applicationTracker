import org.jboss.seam.security.events.DeferredAuthenticationEvent;
import org.jboss.seam.security.external.api.ResponseHolder;
import org.jboss.seam.security.external.openid.OpenIdAuthenticator;
import org.jboss.seam.security.external.openid.api.OpenIdPrincipal;
import org.jboss.seam.security.external.spi.OpenIdRelyingPartySpi;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: twhitlock
 * Date: 6/7/12
 * Time: 9:34 PM
 */
public class OpenIdRelayingPartyImpl implements OpenIdRelyingPartySpi {
    @Inject
    private ServletContext servletContext;

    @Inject
    OpenIdAuthenticator openIdAuthenticator;

    @Inject
    Event<DeferredAuthenticationEvent> deferredAuthentication;

    @Override
    public void loginSucceeded(OpenIdPrincipal principal, ResponseHolder responseHolder) {
        try {

            openIdAuthenticator.success(principal);
            deferredAuthentication.fire(new DeferredAuthenticationEvent());
            responseHolder.getResponse().sendRedirect(servletContext.getContextPath() + "/UserInfo.jsf");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void loginFailed(String message, ResponseHolder responseHolder) {
        try {
            responseHolder.getResponse().sendRedirect(servletContext.getContextPath() + "/AuthenticationFailed.jsf");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
