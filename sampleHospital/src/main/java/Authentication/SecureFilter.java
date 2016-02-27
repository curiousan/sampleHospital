/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Authentication;

import com.mycompany.samplehospital.model.User;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.glassfish.jersey.internal.util.Base64;
import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.annotation.security.PermitAll;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.container.ResourceInfo;

/**
 *
 * @author sandeshpoudel
 */

@Provider

public class SecureFilter implements ContainerRequestFilter {

    private static final String Auth_Header = "Authorization";
    private static final String Auth_Header_Prefix = "Basic ";
    private ResourceInfo resourceInfo;
    private Authentication authenticate;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Response unauthorizedstatus = Response
                .status(Response.Status.UNAUTHORIZED)
                .entity("these resources needs authorization. ")
                .build();
       Response excessUnableStatus = Response.status(Response.Status.FORBIDDEN).entity("this resource has benn forbidden to access").build();
        Method method = resourceInfo.getResourceMethod();
        if (requestContext.getUriInfo().getPath().contains("users")) {
            if (!method.isAnnotationPresent(PermitAll.class)) {
                List<String> authHeader = requestContext.getHeaders().get(Auth_Header);
                if (authHeader != null && authHeader.size() > 0) {
                    String authToken = authHeader.get(0);
                    authToken = authToken.replaceFirst(Auth_Header_Prefix, "");
                    String decodedString = Base64.decodeAsString(authToken);
                    StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
                    String userName = tokenizer.nextToken();
                    String password = tokenizer.nextToken();
                    RolesAllowed roleAnnotation = method.getAnnotation(RolesAllowed.class);
                    Set<String> roleSet = new HashSet<>(Arrays.asList(roleAnnotation.value()));
                    if (userAuthentication(userName, password, roleSet)) {
                        return;
                    } else if (method.isAnnotationPresent(DenyAll.class)) {
                        requestContext.abortWith(excessUnableStatus);

                    }
                }
                requestContext.abortWith(unauthorizedstatus);
            }
        }

    }

    private boolean userAuthentication(String userName, String password, Set<String> roleSet) {
        boolean isAllowed = false;
        try {
            authenticate = new Authentication();
            User newUser = authenticate.getUser(userName, password);
            if (roleSet.contains(newUser.getRole())) {
                isAllowed = true;
                return isAllowed;
            }
        } catch (Exception ex) {

            ex.printStackTrace();
        }
        return isAllowed;

    }

}
