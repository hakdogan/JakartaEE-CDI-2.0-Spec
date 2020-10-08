package org.jugistanbul.scopes;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 8.10.2020
 **/
public class ScopedNumbers
{
    @Produces
    @ApplicationScoped
    private List<Long> sessionScopedCount = new ArrayList<>();

    @Produces
    @RequestScoped
    private List<Integer> requestScopedCount = new ArrayList<>();
}
