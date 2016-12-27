package io.silverware.demos.quickstarts.cdi;

import io.silverware.microservices.annotations.Microservice;
import io.silverware.microservices.annotations.MicroserviceReference;
import io.silverware.microservices.providers.cdi.builtin.CurrentContext;
import io.silverware.microservices.silver.CdiSilverService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Slavomír Krupa (slavomir.krupa@gmail.com)
 */
@Microservice
@Path("health")
public class HealthStatus {

   @Inject
   @MicroserviceReference
   private CurrentContext context;

   @GET
   @Path("status")
   @Produces(MediaType.TEXT_PLAIN)
   public Response status() {
      final boolean isDeployed = ((CdiSilverService) this.context.getContext().getProvider(CdiSilverService.class)).isDeployed();
      return Response.status(isDeployed ? Response.Status.OK : Response.Status.FORBIDDEN).entity(String.valueOf(isDeployed)).build();
   }
}