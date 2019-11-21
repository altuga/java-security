package com.airhacks.ping.boundary;

import com.airhacks.data.Mutable;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

/**
 *
 * @author airhacks.com
 */
@Stateless
@Path("ping")
public class PingResource {

    @Inject
    Mutable mutable;

    @GET
    public String ping() {
        return "Enjoy Java EE 8!";
    }

    @GET
    @Path("mutable")
    public String getMutableData() {
        // danger :
        // what if modify the mutable array Content ?
        //mutable.setArray(null);  // - Provide sensitive mutable classes with unmodifiable wrappers

        StringBuffer result = new StringBuffer();
        for (int i = 0; i < mutable.getArray().length; i++) {
            result.append(mutable.getArray()[i] +"-");
        }
        return result.toString();
    }

    @POST
    public Response newPost(@Context UriInfo info, String data) {

        // Ensure that security-sensitive methods are called with validated arguments
        // curl -H "Content-Type:application/json"  -X POST -v http://localhost:8080/rest-client-for-web/resources/ping/
        // curl -H "Content-Type:application/json" -d '{"numberOfSeats":33}' -X POST -v http://localhost:8080/rest-client-for-web/resources/ping/

       // TODO 1


        System.out.println(" " + data);
        URI uri = info.getAbsolutePathBuilder().
                path(String.valueOf(14)).
                build();
        return Response.created(uri).build();

    }

}
