
# Helidon SE Employee Example

This project implements a simple Employee REST service using Helidon SE.

## Prerequisites

1. Maven 3.5 or newer
2. Java SE 8 or newer
3. Docker 17 or newer to build and run docker images
4. Kubernetes minikube v0.24 or newer to deploy to Kubernetes (or access to a K8s 1.7.4 or newer cluster)
5. Kubectl 1.7.4 or newer to deploy to Kubernetes

Verify prerequisites
```
java -version
mvn --version
docker --version
minikube version
kubectl version --short
```

## Build

```
mvn package
```

## Start the application

```
java -jar target/helidon-examples-employee-app.jar 
```

## Exercise the application

```
curl http://localhost:8080/employees/lastname/S

curl -v -X POST -d '{"id":"501","firstName":"Toyo","lastName":"Herzog","title":"Dynamic Operations Dude","department":"Paradigm Analysis","birthDate":"1961-08-08T11:39:27.324Z","phone":"769-569-1788","email":"Toyo.Herzog@example.com"}' http://localhost:8080/employees

curl -v -X PUT -d '{"id":"11c9cf10-fbbd-4ffa-8ef6-038a4bce9713","firstName":"Toyoyo","lastName":"Herzog","title":"Dynamic Operations Dude","department":"Paradigm Analysis","birthDate":"1961-08-08T11:39:27.324Z","phone":"769-569-1788","email":"Toyo.Herzog@example.com"}' http://localhost:8080/employees/101

curl -v -X DELETE http://localhost:8080/employees/11c9cf10-fbbd-4ffa-8ef6-038a4bce9713
```

## Try health and metrics

```
curl -s -X GET http://localhost:8080/health
{"outcome":"UP",...
. . .

# Prometheus Format
curl -s -X GET http://localhost:8080/metrics
# TYPE base:gc_g1_young_generation_count gauge
. . .

# JSON Format
curl -H 'Accept: application/json' -X GET http://localhost:8080/metrics
{"base":...
. . .

```

## Build the Docker Image

```
docker build -t employee-app .
```

## Start the application with Docker

```
docker run --rm -p 8080:8080 employee-app:latest
```

Exercise the application as described above

## Deploy the application to Kubernetes

```
kubectl cluster-info                # Verify which cluster
kubectl get pods                    # Verify connectivity to cluster
kubectl create -f app.yaml   # Deply application
kubectl get service employee-app  # Get service info
```

## Native image with GraalVM

GraalVM allows you to compile your programs ahead-of-time into a native
 executable. See https://www.graalvm.org/docs/reference-manual/aot-compilation/
 for more information.

You can build a native executable in 2 different ways:
* With a local installation of GraalVM
* Using Docker

### Local build

Download Graal VM at https://github.com/oracle/graal/releases, the version
 currently supported for Helidon is `19.0.0`.

```
# Setup the environment
export GRAALVM_HOME=/path
# build the native executable
mvn package -Pnative-image
```

You can also put the Graal VM `bin` directory in your PATH, or pass
 `-DgraalVMHome=/path` to the Maven command.

See https://github.com/oracle/helidon-build-tools/tree/master/helidon-maven-plugin
 for more information.

Start the application:

```
./target/employee-app
```

### Multi-stage Docker build

Build the "native" Docker Image

```
docker build -t employee-app-native -f Dockerfile.native .
```

Start the application:

```
docker run --rm -p 8080:8080 employee-app-native:latest
```