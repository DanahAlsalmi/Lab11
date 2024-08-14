package com.example.lab11.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @NotNull
    @Column(columnDefinition = "int not null")
    private int userId;

    @NotNull
    @Column(columnDefinition = "int not null")
    private int postId;

    @NotEmpty
    @Column(columnDefinition = "text not null")
    private String content;

    private LocalDateTime commentDate;
}
