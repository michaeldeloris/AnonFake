package util.errors;

public enum Error {
  // REGISTER ERRORS 
  NO_USERNAME_GIVEN("You must enter a username."),
  NO_PASSWORD_GIVEN("You must enter a password."),
  USERNAME_ALREADY_TAKEN("Username already exists."),
  MISSMATCH_PASSWORDS("Passwords doesn't match."),
  
  // UNKNOWN ERROR
  UNKNOWN_ERROR("An unknown error interrupted the operation. Please contact an administrator.");
  
  
  private final String description;
  
  private Error(String description) {
    this.description = description;
  }
  
  public String toString() {
    return description;
  }
}
