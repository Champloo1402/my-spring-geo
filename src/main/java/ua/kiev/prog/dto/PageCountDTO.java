package ua.kiev.prog.dto;

import lombok.Data;

@Data
public class PageCountDTO {
    private long count;
    private int pageSize;

    private PageCountDTO(long count, int pageSize) {
        this.count = count;
        this.pageSize = pageSize;
    }

    public static PageCountDTO of(long count, int pageSize) {
        return new PageCountDTO(count, pageSize);
    }
}
