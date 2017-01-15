package de.thaso.jd.web;

import de.thaso.jd.web.model.SimpleModel;
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
public class ChatController {

    private static final Logger LOG = LoggerFactory.getLogger(ChatController.class);

    @Inject
    private SimpleModel chatOverviewModel;
}
