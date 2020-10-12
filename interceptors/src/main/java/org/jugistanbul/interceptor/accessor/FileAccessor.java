package org.jugistanbul.interceptor.accessor;

import org.jugistanbul.interceptor.Breaker;
import org.jugistanbul.interceptor.Checker;
import org.slf4j.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 11.10.2020
 **/
@ApplicationScoped
public class FileAccessor
{
    private static final String PATH = "src/main/resources";

    @Inject
    private Checker checker;

    @Inject
    private Logger logger;

    @Breaker
    public String readString(final String name) {
        var fullPath = String.join("/",
                System.getProperty("user.dir")
                        .substring(0, System.getProperty("user.dir").lastIndexOf("/target")),
                PATH, name);

        var checkerMap = checker.getCheckerMap();
        var result = "The file in " + fullPath + " path could not be read";
        try {
            result = Files.readString(Paths.get(fullPath));
            Optional.ofNullable(checkerMap.get(name)).ifPresent(n -> n.remove(name));
        } catch (IOException ioe) {
            checkerMap.put(name, updateFaultTolerantObject(checkerMap.get(name)));
            logger.error("IOException was thrown! {}", checkerMap.get(name).getInt("count"));
        }

        logger.info(result);
        return result;
    }

    private JsonObject updateFaultTolerantObject(final JsonObject faultObject){
        var count = Objects.nonNull(faultObject)?faultObject.getInt("count"):0;
        return Json.createObjectBuilder()
                .add("count", ++count)
                .add("time", String.valueOf(LocalDateTime.now()))
                .build();
    }
}
