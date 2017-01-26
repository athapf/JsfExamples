package de.thaso.jd.web.first;

import de.thaso.jd.web.first.model.SimpleModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * ChatController
 *
 * @author thaler
 * @since 23.09.16
 */
@Named
@RequestScoped
public class SimpleController {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleController.class);

    @Inject
    private SimpleModel chatOverviewModel;
}
