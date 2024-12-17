package xyz.clavis.security.models;

public class ClavisPassword {
  private String password;

  public ClavisPassword(String password) {
    this.password = password;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
