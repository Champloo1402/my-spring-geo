package ua.kiev.prog.model;

import lombok.Data;

@Data
public class PageCount {
    private long count;
    private int pageSize;

    public PageCount(long count, int pageSize) {
        this.count = count;
        this.pageSize = pageSize;
    }
}
