package com.example.taskify.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDTO {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime completedDate;
    private boolean completed;

}