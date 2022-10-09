package com.bravosystems.keycloak;

import lombok.extern.log4j.Log4j;
import org.keycloak.Config;
import org.keycloak.authentication.FormAction;
import org.keycloak.authentication.FormActionFactory;
import org.keycloak.authentication.FormContext;
import org.keycloak.authentication.ValidationContext;
import org.keycloak.forms.login.LoginFormsProvider;
import org.keycloak.models.*;
import org.keycloak.models.utils.FormMessage;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.services.resources.AttributeFormDataProcessor;
import org.keycloak.services.validation.Validation;
import org.keycloak.userprofile.UserProfile;
import org.keycloak.userprofile.profile.UserProfileContextFactory;
import org.keycloak.userprofile.profile.representations.AttributeUserProfile;
import org.keycloak.userprofile.utils.UserUpdateHelper;
import org.keycloak.userprofile.validation.UserProfileValidationResult;

import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

@Log4j
public class PiriaFormAction implements FormAction, FormActionFactory {

   public static final String FIELD_EMAIL = "email";
   public static final String PROVIDER_ID = "registration-profile-action";

   @Override
   public String getHelpText() {
      return "Custom FormAction.";
   }

   @Override
   public List<ProviderConfigProperty> getConfigProperties() {
      return null;
   }

   public void validate(ValidationContext context) {
      MultivaluedMap<String, String> formData = context.getHttpRequest().getDecodedFormParameters();
      context.getEvent().detail("register_method", "form");
      UserProfileValidationResult result = UserProfileContextFactory.forRegistrationProfile(context.getSession(), formData).validate();
      List<FormMessage> errors = Validation.getFormErrorsFromValidation(result);

      String username = formData.getFirst("username");

      if (username.length() < 12 || username.matches(".*[@#/].*")) {
         errors.add(new FormMessage("username", "invalidUsernameMessage"));
      }

      if (errors.size() > 0) {
         if (result.hasFailureOfErrorType(new String[]{"emailExistsMessage", "invalidEmailMessage"})) {
            UserProfile updatedProfile = result.getProfile();
            context.getEvent().detail("email", updatedProfile.getAttributes().getFirstAttribute("email"));
         }

         if (result.hasFailureOfErrorType(new String[]{"emailExistsMessage"})) {
            context.error("email_in_use");
            formData.remove("email");
         } else {
            context.error("invalid_registration");
         }

         context.validationError(formData, errors);
      } else {
         context.success();
      }
   }

   public void success(FormContext context) {
      UserModel user = context.getUser();
      AttributeUserProfile updatedProfile = AttributeFormDataProcessor.toUserProfile(context.getHttpRequest().getDecodedFormParameters());
      UserUpdateHelper.updateRegistrationProfile(context.getRealm(), user, updatedProfile);
   }

   @Override
   public void buildPage(FormContext context, LoginFormsProvider form) {
   }

   @Override
   public boolean requiresUser() {
      return false;
   }

   @Override
   public boolean configuredFor(KeycloakSession session, RealmModel realm, UserModel user) {
      return true;
   }

   @Override
   public void setRequiredActions(KeycloakSession session, RealmModel realm, UserModel user) {
   }

   @Override
   public boolean isUserSetupAllowed() {
      return false;
   }


   @Override
   public void close() {
   }

   @Override
   public String getDisplayType() {
      return "Eureka Profile Validation";
   }

   @Override
   public String getReferenceCategory() {
      return null;
   }

   @Override
   public boolean isConfigurable() {
      return false;
   }

   private static final AuthenticationExecutionModel.Requirement[] REQUIREMENT_CHOICES = {
         AuthenticationExecutionModel.Requirement.REQUIRED,
         AuthenticationExecutionModel.Requirement.DISABLED
   };

   @Override
   public AuthenticationExecutionModel.Requirement[] getRequirementChoices() {
      return REQUIREMENT_CHOICES;
   }

   @Override
   public FormAction create(KeycloakSession session) {
      return this;
   }

   @Override
   public void init(Config.Scope config) {
   }

   @Override
   public void postInit(KeycloakSessionFactory factory) {
   }

   @Override
   public String getId() {
      return PROVIDER_ID;
   }

}
