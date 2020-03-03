package io.pivotal.pcf.demo.fortunebackend;

/**
 * WavefrontInfo is a simple object class
 * which contains some of the information coming from
 * Wavefront side (e.g. configurations) that the application might need
 * to do any activities (e.g. sending metric, creating alerts) to it.
 */
public class WavefrontInfo {
  private String url;
  private String oneTimeLink;
  private String token;
  private String source;
  private boolean isOneTimeClicked = false;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getOneTimeLink() {
    return oneTimeLink;
  }

  public void setOneTimeLink(String oneTimeLink) {
    this.oneTimeLink = oneTimeLink;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  public void setOneTimeClicked(boolean clicked) {
    this.isOneTimeClicked = clicked;
  }

  public boolean getOneTimeClicked() {
    return isOneTimeClicked;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }
}
