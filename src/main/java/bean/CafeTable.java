package bean;

import java.io.Serializable;

public class CafeTable implements Serializable {

    private int tableId;
    private String status;

    public CafeTable() {
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}