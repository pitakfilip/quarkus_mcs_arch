# quarkus_mcs_arch
FI MUNI Team project for SOA/MCS Architecture developed with Quarkus and Kubernetes

### Tech Stack
- JDK21 - Temurin
- Docker
- TODO

### Service structure (DDD Architecture)
- Domain package: contains the base classes and functionality with them. This package 
contains no technological dependencies and is fully testable without the need of 
implementations such as repositories, other services and so.
- API package: represents the contract of the service, containing the API interfaces 
and DTO `(Data Transfer Object)` definitions.
- Application package: runnable application implementing the defined API, providing 
described functionality.
