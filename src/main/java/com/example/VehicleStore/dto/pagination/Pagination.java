package com.example.VehicleStore.dto.pagination;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class Pagination {
    private Integer offset;
    private Integer limit;

    public Pageable pageable(Sort sort) {
        int offset = (this.offset != null) ? this.offset : 0;
        int limit = (this.limit != null) ? this.limit : 10;

        return PageRequest.of(offset, limit, sort);
    }

}
