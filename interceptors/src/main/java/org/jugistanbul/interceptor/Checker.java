package org.jugistanbul.interceptor;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 11.10.2020
 **/
@ApplicationScoped
public class Checker
{

    @Inject
    @ConfigProperty(name = "faultTolerance")
    private int faultTolerant;

    @Inject
    @ConfigProperty(name = "timeOutMs")
    private long timeOutMs;

    private Map<String, JsonObject> checkerMap = new HashMap<>();

    public Map<String, JsonObject> getCheckerMap() {
        return checkerMap;
    }

    public int getFaultTolerant() {
        return faultTolerant;
    }

    public long getTimeOutMs() {
        return timeOutMs;
    }
}
