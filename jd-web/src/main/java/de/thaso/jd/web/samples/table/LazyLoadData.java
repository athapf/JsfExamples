package de.thaso.jd.web.samples.table;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by thaler on 10.03.17.
 */
public class LazyLoadData extends LazyDataModel<SimpleTableRow> {

    private static final Logger LOG = LoggerFactory.getLogger(LazyLoadData.class);

    private LazyTableModel lazyTableModel;

    public LazyLoadData(LazyTableModel lazyTableModel) {
        this.lazyTableModel = lazyTableModel;
    }

    @Override
    public Object getRowKey(SimpleTableRow object) {
        LOG.info("----------------- getRowKey");
        return (Integer)object.getIndex();
    }

    @Override
    public SimpleTableRow getRowData(String rowKey) {
        LOG.info("----------------- getRowData: {} -> {}", rowKey, super.getRowData(rowKey));
        return super.getRowData(rowKey);
    }

    @Override
    public boolean isRowAvailable() {
        LOG.info("----------------- isRowAvailable: {} - {}", super.isRowAvailable(), getRowIndex());
        return super.isRowAvailable();
    }

    @Override
    public SimpleTableRow getRowData() {
        LOG.info("----------------- getRowData: {}", super.getRowData());
        return super.getRowData();
    }

    @Override
    public void setRowIndex(final int rowIndex) {
        LOG.info("-------------->>- setRowIndex: {}", rowIndex);
        super.setRowIndex(rowIndex);
    }

    @Override
    public List<SimpleTableRow> load(int first, int pageSize, final String sortField, final SortOrder sortOrder, Map<String, Object> filters) {
        LOG.info("load in lazy load model: first={}, pageSize={}, sortField={}, sortOrder={}", first, pageSize, sortField, sortOrder);

        final int dataSize = lazyTableModel.getDatabase().size();
        setRowCount(dataSize);

        if(first > dataSize) {
            setWrappedData(null);
            return null;
        }

        if(sortField != null) {
            Collections.sort(lazyTableModel.getDatabase(), new Comparator<SimpleTableRow>() {
                @Override
                public int compare(SimpleTableRow o1, SimpleTableRow o2) {
                    int erg = 0;
                    if("name".equals(sortField)) {
                        erg = o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
                    } else if("description".equals(sortField)) {
                        erg = o1.getDescription().toLowerCase().compareTo(o2.getDescription().toLowerCase());
                    } else if("location".equals(sortField)) {
                        erg = o1.getLocation().toLowerCase().compareTo(o2.getLocation().toLowerCase());
                    } else if("index".equals(sortField)) {
                        erg = o1.getIndex() - o2.getIndex();
                    }
                    if(SortOrder.DESCENDING.equals(sortOrder)) {
                        return -1 * erg;
                    }
                    return erg;
                }
            });
        }

        final List<SimpleTableRow> rowList = new ArrayList<>();

        if(first < dataSize) {
            final int toIndex = first + pageSize;
            rowList.addAll(lazyTableModel.getDatabase().subList(first, Math.min(toIndex, dataSize)));
        }
        LOG.info("{} more rows loaded starting from {} // pageSize={}, rowCount={}, rowIndex={}, wrappedData={}, lastStoredIndex={}",
                rowList.size(), first,
                getPageSize(), getRowCount(), getRowIndex(),
                getWrappedData() != null ? ((List)getWrappedData()).size() : null,
                getWrappedData() != null && !((List)getWrappedData()).isEmpty() ? ((List<SimpleTableRow>)getWrappedData()).get(((List)getWrappedData()).size()-1).getIndex() : null
        );
        setWrappedData(rowList);
        return rowList;
    }

    @Override
    public int getRowCount() {
        LOG.info("............. getRowCount: {}", super.getRowCount());
        return super.getRowCount();
        //return 50;
    }

    @Override
    public Object getWrappedData() {
        LOG.info("............. getWrappedData");
        return super.getWrappedData();
    }

    @Override
    public int getPageSize() {
        LOG.info("............. getPageSize: {}", super.getPageSize());
        return super.getPageSize();
        //return 15;
    }
}
