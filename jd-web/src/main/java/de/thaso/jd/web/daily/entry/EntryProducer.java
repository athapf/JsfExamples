package de.thaso.jd.web.daily.entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 * EntryProducer
 *
 * @author thaler
 * @since 27.02.17
 */
public class EntryProducer {

    private static final Logger LOG = LoggerFactory.getLogger(EntryProducer.class);

    @Produces
    @SessionScoped
    @Named("dpEntryModel")
    public EntryModel produceEntryModel(@New EntryModel entryModel) {
        return entryModel;
    }
}
