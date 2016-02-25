/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Authentication;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.glassfish.jersey.internal.util.Base64;

/**
 *
 * @author sandeshpoudel
 */
@Provider
public class SecureFilter implements ContainerRequestFilter {

    private static final String Auth_Header = "Authorization";
    private static final String Auth_Header_Prefix = "Basic ";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (requestContext.getUriInfo().getPath().contains("users")) {
            List<String> authHeader = requestContext.getHeaders().get(Auth_Header);
            if (authHeader != null && authHeader.size() > 0) {
                String authToken = authHeader.get(0);
                authToken = authToken.replaceFirst(Auth_Header_Prefix, "");
                String decodedString = Base64.decodeAsString(authToken);
                StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
                String userName = tokenizer.nextToken();
                String password = tokenizer.nextToken();
                if ("user".equals(userName) && "password".equals(password)) {
                    return;
                }
                Response unauthorizedstatus = Response
                        .status(Response.Status.UNAUTHORIZED)
                        .entity("these resources needs authorization. ")
                        .build();
                requestContext.abortWith(unauthorizedstatus);

            }
        }

    }

}
