package ua.kiev.prog.model;

import lombok.Data;

@Data
public class PageCount {
    private long count;

    public PageCount(long count) {
        this.count = count;
    }
}
