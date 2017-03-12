package de.thaso.jd.web.samples.table;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

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
        return "" + object.getIndex();
    }

    @Override
    public SimpleTableRow getRowData(String rowKey) {
        LOG.info("----------------- getRowData");
        return super.getRowData(rowKey);
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
}
