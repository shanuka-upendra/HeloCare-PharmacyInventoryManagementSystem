package com.dev.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Medicine {
    private Integer id;
    private String name;
    private String brandName;
    private String category;
    private String description;
}
