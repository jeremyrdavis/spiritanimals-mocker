package io.arrogantprogrammer.spiritanimals.mocker;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/spiritanimals")
@RegisterRestClient
public interface WorkflowApiClient {

    @POST
    @Path("/assign")
    public WorkflowRecord assignSpiritAnimal(final String name);

    @POST
    @Path("/whatIs")
    public WorkflowRecord whatIs(final Long id);

    @POST
    @Path("/poem")
    public WorkflowRecord aPoem(final Long id);

    @POST
    @Path("/addToPoem")
    public WorkflowRecord anotherPoem(final Long id);

    @POST
    @Path("/like")
    public WorkflowRecord like(final Long id);

    @POST
    @Path("/feedback")
    public WorkflowRecord feedback(FeedbackJson feedbackJson);

}
