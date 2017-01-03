package io.silverware.demos.quickstarts.openshift.cluster.api;

/**
 * Interface for clustered service
 *
 * @author Slavomír Krupa (slavomir.krupa@gmail.com)
 */
public interface WorkerInterface {
   String HELLO = "Hello World!";
   String VERSION_1 = "1.1";
   String VERSION_2 = "2.5";

   String callHello();

   String getVersion();

   CustomObject customSerialization(CustomObject customObject);

}
