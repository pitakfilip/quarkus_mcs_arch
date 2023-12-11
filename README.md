# quarkus_mcs_arch
FI MUNI Team project for SOA/MCS Architecture developed with Quarkus and Kubernetes

## Tech Stack
- JDK21 - Temurin
- Docker
- TODO

## Service structure (DDD Architecture)
- Domain package: contains the base classes and functionality with them. This package 
contains no technological dependencies and is fully testable without the need of 
implementations such as repositories, other services and so.
- API package: represents the contract of the service, containing the API interfaces 
and DTO `(Data Transfer Object)` definitions.
- Application package: runnable application implementing the defined API, providing 
described functionality.

## Description of the project
Since all members of our team are game enjoyers, we decided to have some fun with this opportunity. We decided we would create a microservice project based on real-time strategy video games (For example Empire Earth).
### Warfare microservice
- This microservice represents an army of a kingdom. It handles functionalities like training new battle troops, leveling up their skills, manages the impact of a war between kingdoms etc.
### Foundation microservice
- This microservice represents some sort of infrastructure of a given kingdom, manages buildings, training barracks, available resources of a kingdom.
### Kingdom microservice
- Represents a kingdom. This is the main service that handles creation of new kingdoms.
### Simulation microservice
- Simulates battles between kingdoms, deciding the winner based on who's army is stronger.
