package com.ftzp.pojo.rg;

public class Columns {
    private Integer columnId;
    private String columnsName;
    private Integer channelId;

    public Integer getColumnId() {
        return columnId;
    }

    public void setColumnId(Integer columnId) {
        this.columnId = columnId;
    }

    public String getColumnsName() {
        return columnsName;
    }

    public void setColumnsName(String columnsName) {
        this.columnsName = columnsName;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    @Override
    public String toString() {
        return "Column{" +
                "columnId=" + columnId +
                ", columnsName='" + columnsName + '\'' +
                ", channelId=" + channelId +
                '}';
    }
}
