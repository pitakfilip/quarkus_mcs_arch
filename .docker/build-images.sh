cd ..
HOME=$(PWD)

#cd $HOME/infrastructure
#mvn clean install
#
#cd $HOME/foundation
#mvn clean install
cd $HOME/foundation/foundation-application
./mvnw install -Dquarkus.container-image.build=true -Dquarkus.container-image.group=wildTribes

#
#cd $HOME/warfare
#mvn clean install
#cd $HOME/warfare/warfare-application
#mvn install -Dquarkus.container-image.build=true -Dquarkus.container-image.group=wildTribes
#
#
#cd $HOME/kingdom
#mvn clean install
#cd $HOME/kingdom/kingdom-application
#mvn install -Dquarkus.container-image.build=true -Dquarkus.container-image.group=wildTribes
#
#
#cd $HOME/simulation
#mvn clean install
#cd $HOME/simulation/simulation-application
#mvn install -Dquarkus.container-image.build=true -Dquarkus.container-image.group=wildTribes