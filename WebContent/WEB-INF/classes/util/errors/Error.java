package util.errors;

public enum Error {
  //LOGIN
  CANNOT_LOG("Invalid username or password."),
  
  // REGISTER 
  NO_USERNAME_GIVEN("You must enter a username."),
  NO_PASSWORD_GIVEN("You must enter a password."),
  USERNAME_ALREADY_TAKEN("Username already exists."),
  MISSMATCH_PASSWORDS("Passwords doesn't match."),
  
  //DATABASE
  DATABASE_UNREACHABLE("Cannot etablish connection to database."),
  
  // UNKNOWN
  UNKNOWN_ERROR("An unknown error interrupted the operation. Please contact an administrator.");
  
  
  private final String description;
  
  private Error(String description) {
    this.description = description;
  }
  
  @Override
  public String toString() {
    return description;
  }
}
