package com.airhacks.ping.boundary;

import com.airhacks.data.Customer;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

@Stateless
@Path("bank")
@Consumes(MediaType.APPLICATION_JSON)
public class BankResource {

    private Map database = new HashMap<String, String>();


    @GET
    public String ping() {
        return "Enjoy Bank App!";
    }

    @POST
    public Response newPost(@Context UriInfo info, Customer customer) {

        // Ensure that security-sensitive methods are called with validated arguments
        // curl -H "Content-Type:application/json" -d '{"id":1, "name":"<b onmouseover=alert('Wufff!')>click me!</b>"}' -X POST -v http://localhost:8080/rest-client-for-web/resources/bank


        System.out.println(" " + customer);

         //normalize(customer);
         //HTMLEntityEncode(customer);



        database.put("data", customer);
        URI uri = info.getAbsolutePathBuilder().
                path(String.valueOf(customer.getId())).
                build();
        return Response.created(uri).build();

    }

    // TODO 02

}
