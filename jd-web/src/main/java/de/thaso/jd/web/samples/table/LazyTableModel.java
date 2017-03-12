package de.thaso.jd.web.samples.table;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Any;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thaler on 10.03.17.
 */
@Any
public class LazyTableModel implements Serializable {

    private static final long serialVersionUID = 8362924133942674057L;
    private static final Logger LOG = LoggerFactory.getLogger(LazyTableModel.class);

    private List<SimpleTableRow> database = new ArrayList<>();

    private LazyLoadData lazyLoadData = new LazyLoadData(this);

    private int blockSize = 15;
    private int initRows = 15;
    private int maxRows = 50;

    public int getBlockSize() {
        return blockSize;
    }

    public int getInitRows() {
        return initRows;
    }

    public int getMaxRows() {
        return maxRows;
    }

    public LazyLoadData getLazyLoadData() {
        return lazyLoadData;
    }

    public List<SimpleTableRow> getDatabase() {
        return database;
    }

    private SimpleTableRow createSimpleRow(final int index) {
        final SimpleTableRow result = new SimpleTableRow();
        result.setIndex(index + 1);
        result.setDescription(RandomStringUtils.randomAlphabetic(20));
        result.setName(RandomStringUtils.randomAlphabetic(20));
        result.setLocation(RandomStringUtils.randomAlphabetic(20));
        return result;
    }

    public void init() {
        for(int i = 0; i < maxRows; i++) {
            database.add(createSimpleRow(i));
        }
    }

    public void reset() {
        lazyLoadData = new LazyLoadData(this);
    }
}
