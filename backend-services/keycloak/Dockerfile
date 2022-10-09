FROM docker.io/jboss/keycloak:13.0.1

COPY messages opt/jboss/keycloak/themes/base/login/messages
COPY /target/keycloak-adapters.jar /opt/jboss/keycloak/standalone/deployments/

CMD ["-b", "0.0.0.0"]