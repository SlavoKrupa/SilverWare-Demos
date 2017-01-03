package io.silverware.demos.quickstarts.openshift.cluster.api;

import static io.silverware.demos.quickstarts.openshift.cluster.api.WorkerInterface.VERSION_1;

import io.silverware.microservices.annotations.Microservice;
import io.silverware.microservices.annotations.MicroserviceVersion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Older implementation of worker.
 *
 * NOTE: Do not mix multiple version of services in same project.
 *
 * @author Slavomír Krupa (slavomir.krupa@gmail.com)
 */
@Microservice
@MicroserviceVersion(api = VERSION_1)
public class WorkerOld implements WorkerInterface {

   private static final Logger log = LogManager.getLogger(WorkerOld.class);

   @Override
   public String callHello() {
      log.info("Hello!");
      return HELLO;

   }

   @Override
   public String getVersion() {
      return VERSION_1;
   }

   @Override
   public CustomObject customSerialization(final CustomObject customObject) {
      CustomObject result = CustomObject.randomObject();
      log.info("Received: {} returning: {} ", customObject, result);
      return result;
   }
}
