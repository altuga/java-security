package com.airhacks.ping.boundary;

import com.airhacks.data.Mutable;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author airhacks.com
 */
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

}
