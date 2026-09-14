package com.oreki5.keionbu.dbEntities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class Teachers extends Users {
    private String subject;

    @DBRef(lazy = true)
    private List<Students> students = new ArrayList<>();;

}
