package com.example.lab11.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @NotNull
    @Column(columnDefinition = "int not null")
    private int categoryId;

    @NotEmpty
    @Column(columnDefinition = "varchar(25) not null")
    private String title;

    @NotEmpty
    @Column(columnDefinition = "text not null")
    private String content;

    @NotNull
    @Column(columnDefinition = "int not null")
    private int userId;

    private LocalDate publishDate;
}
