package edu.miu.cs545.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostV2 {
    private long id;
    private String title;
    private String content;
    private String author;
}
