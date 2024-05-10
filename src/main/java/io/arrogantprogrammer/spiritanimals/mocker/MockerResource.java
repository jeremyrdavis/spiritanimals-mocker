package io.arrogantprogrammer.spiritanimals.mocker;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/mock")
public class MockerResource {

    @Inject
    MockerService mockerService;


    @POST
    @Path("/start")
    public Response start() {
        mockerService.start();
        Log.debugf("Started mocking...");
        return Response.accepted().build();
    }

    @POST
    @Path("/stop")
    public Response stop() {
        mockerService.stop();
        Log.debugf("Started mocking...");
        return Response.accepted().build();
    }
}
