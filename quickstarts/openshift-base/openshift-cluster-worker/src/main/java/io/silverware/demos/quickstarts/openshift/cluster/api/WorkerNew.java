package io.silverware.demos.quickstarts.openshift.cluster.api;

import static io.silverware.demos.quickstarts.openshift.cluster.api.WorkerInterface.VERSION_2;

import io.silverware.microservices.annotations.Microservice;
import io.silverware.microservices.annotations.MicroserviceVersion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Newer implementation of worker.
 *
 * NOTE: Do not mix multiple version of services in same project.
 * In this project it is done just for minimalization of example.
 *
 * @author Slavomír Krupa (slavomir.krupa@gmail.com)
 */
@Microservice
@MicroserviceVersion(api = VERSION_2)
public class WorkerNew implements WorkerInterface {

   private static final Logger log = LogManager.getLogger(WorkerNew.class);

   @Override
   public String callHello() {
      log.info("Hello!");
      return HELLO;

   }

   @Override
   public String getVersion() {
      return VERSION_2;
   }

   @Override
   public CustomObject customSerialization(final CustomObject customObject) {
      log.info("Received and returning same object {} ", customObject);
      return customObject;
   }
}
