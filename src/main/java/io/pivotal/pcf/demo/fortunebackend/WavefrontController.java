package io.pivotal.pcf.demo.fortunebackend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import io.micrometer.wavefront.WavefrontConfig;

/**
 * rest service to retrieve wavefront info
 */
@Controller
public class WavefrontController {

  private static Logger LOG = LoggerFactory.getLogger(WavefrontController.class);
  // wavefront config may not exist, as autoconfig can be disabled.
  @Autowired(required=false)
  private WavefrontConfig wfconfig;
  @Autowired
  private ApplicationContext context;

  // main bean to keep wavefront info within this application.
  @Bean
  public WavefrontInfo wavefrontInfo() {
    WavefrontInfo info = new WavefrontInfo();
    return info;
  }

  /**
   * Service to retrieve wavefront config information
   * @return
   */
  @RequestMapping(value = "/wf-info", method = RequestMethod.GET)
  @ResponseBody
  @CrossOrigin
  public WavefrontInfo getWfInfo() {
    // get wavefront info bean
    WavefrontInfo info = (WavefrontInfo)context.getBean("wavefrontInfo");
    if(wfconfig != null) {
      String onetimelink = wfconfig.get("wavefront.onetimelink");
      String apiToken = wfconfig.apiToken();
      String url = wfconfig.uri();
      String source = wfconfig.source();
      // if onetimelink is clicked by user, we do not show the link anymore.
      if(info.getOneTimeClicked() == false && onetimelink != null) {
        info.setOneTimeLink(onetimelink);
      } else {
        info.setOneTimeLink(null);
        info.setOneTimeClicked(true);
      }
      info.setToken(apiToken);
      info.setUrl(url);
      info.setSource(source);
    }
    // LOG.debug("WavefrontInfo-[%s|%s]".format(info.getUrl(), info.getToken()));
    return info;
  }

  /**
   * service to enable wavefront one time link, so that when the user first click
   * the link, application will switch showing the link to normal wavefront url.
   * @return
   */
  @RequestMapping(value = "/wf-enable", method = RequestMethod.GET)
  @ResponseBody
  @CrossOrigin
  public String enableWavefront() {
    // get wavefront info bean
    WavefrontInfo info = (WavefrontInfo)context.getBean("wavefrontInfo");
    LOG.info("Wavefront one time link is activated: [" + info.getOneTimeLink() + "]");
    info.setOneTimeClicked(true);
    return "true";
  }
}
