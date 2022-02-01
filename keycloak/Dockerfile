FROM docker.io/jboss/keycloak:13.0.1

COPY messages opt/jboss/keycloak/themes/base/login/messages
RUN printf "\nlocale_sr=Srpski" >> opt/jboss/keycloak/themes/base/login/messages/messages_en.properties

CMD ["-b", "0.0.0.0"]