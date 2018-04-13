package ua.kiev.prog.model;

import lombok.Data;

@Data
public class PageCount {
    private long count;
    private int pageSize;

    private PageCount(long count, int pageSize) {
        this.count = count;
        this.pageSize = pageSize;
    }

    public static PageCount of(long count, int pageSize) {
        return new PageCount(count, pageSize);
    }
}
