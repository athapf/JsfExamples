package de.thaso.jd.web.daily.entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
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

    private final static Logger LOG = LoggerFactory.getLogger(EntryProducer.class);
    private final static Object obj = new Object();

    @Produces
    @RequestScoped
    @Named("dpEntryModel")
    public EntryModel produceEntryModel(@New EntryModel entryModel) {

        LOG.info("do some very long work ...");
        synchronized (obj) {
            try {
                obj.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        LOG.info("... long work ready.");
        return entryModel;
    }
}
