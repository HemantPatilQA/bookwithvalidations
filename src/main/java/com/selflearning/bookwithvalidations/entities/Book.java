package com.selflearning.bookwithvalidations.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Please provide a name")
    private String name;

    private String author;

    @NotNull(message = "Please provide a price")
    private double price;

    //@DecimalMin("1.00")
}
