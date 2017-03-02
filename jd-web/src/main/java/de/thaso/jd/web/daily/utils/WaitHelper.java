package de.thaso.jd.web.daily.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * WaitHelper
 *
 * @author thaler
 * @since 02.03.17
 */
public class WaitHelper {

    private static final Logger LOG = LoggerFactory.getLogger(WaitHelper.class);
    private final static Object obj = new Object();

    public static void pause() {
        LOG.info("do some very long work ...");
//        synchronized (obj) {
//            try {
//                obj.wait(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        LOG.info("... long work ready.");
    }
}
