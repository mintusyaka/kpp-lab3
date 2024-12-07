package models;

import java.io.Serializable;

public class Client implements Serializable {
    private int id;
    private String name;
    private int tableId;

    public Client() {}

    public Client(int id, String name, int tableNumber) {
        this.id = id;
        this.name = name;
        this.tableId = tableNumber;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getTableId() {
        return tableId;
    }
    public void setTableId(int tableId) {
        this.tableId = tableId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Id:" + id + "|Name:" + name + "|tableId:" + tableId + "\n";
    }
}
