package edu.miu.cs545.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDtoV2 {
    private String title;
    private String content;
    private String author;
}
