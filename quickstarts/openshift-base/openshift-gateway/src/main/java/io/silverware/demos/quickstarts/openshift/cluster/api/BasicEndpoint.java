package io.silverware.demos.quickstarts.openshift.cluster.api;

import io.silverware.microservices.annotations.InvocationPolicy;
import io.silverware.microservices.annotations.Microservice;
import io.silverware.microservices.annotations.MicroserviceReference;
import io.silverware.microservices.annotations.MicroserviceVersion;
import io.silverware.microservices.silver.services.lookup.RandomRobinLookupStrategy;
import io.silverware.microservices.silver.services.lookup.RoundRobinLookupStrategy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Simple presentation of remote invocations and version resolution.
 *
 * @author Slavomír Krupa (slavomir.krupa@gmail.com)
 */
@Path("call")
@Microservice
public class BasicEndpoint {

   private static final Logger log = LogManager.getLogger(BasicEndpoint.class);
   public static final String VERSION_1_QUERY = "~1";
   public static final String VERSION_2_QUERY = "^2";

   @Inject
   @MicroserviceReference
   @InvocationPolicy(lookupStrategy = RoundRobinLookupStrategy.class)
   @MicroserviceVersion(api = VERSION_2_QUERY)
   WorkerInterface workerV2;

   @Inject
   @MicroserviceReference
   @InvocationPolicy(lookupStrategy = RandomRobinLookupStrategy.class)
   @MicroserviceVersion(api = VERSION_1_QUERY)
   WorkerInterface workerV1;

   @GET
   @Path("remoteRoundHello")
   public Response remoteRoundHello() {
      log.info("Calling round hello on remote nodes");
      return Response.ok(workerV2.callHello()).build();
   }

   @GET
   @Path("remoteRandomHello")
   public Response remoteRandomHello() {
      log.info("Calling random hello on remote nodes");

      return Response.ok(workerV1.callHello()).build();
   }

   @GET
   @Path("getVersion")
   public Response versionCheck() {
      log.info("Obtaining version from remote nodes");
      final String workerV1Version = workerV1.getVersion();
      final String workerV2Version = workerV2.getVersion();
      String versionMessage = String.format(" Query `%s`  resolved version `%s` and query `%s` resolved version `%s`.", VERSION_1_QUERY, workerV1Version, VERSION_2_QUERY, workerV2Version);
      log.info(versionMessage);
      return Response.ok(versionMessage).build();
   }


   @GET
   @Path("newObjectGeneration")
   public Response newObjectGeneration() {
      log.info("Generating custom object.");
      CustomObject randomObject = CustomObject.randomObject();
      CustomObject modifiedObject = workerV1.customSerialization(randomObject);
      String objectMessage = String.format("Generated object was: %s\n and modified object is: %s\n", randomObject, modifiedObject);
      log.info(objectMessage);
      return Response.ok(objectMessage).build();
   }

}
