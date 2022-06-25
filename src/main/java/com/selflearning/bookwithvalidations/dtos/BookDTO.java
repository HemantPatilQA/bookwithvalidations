package com.selflearning.bookwithvalidations.dtos;

import com.selflearning.bookwithvalidations.error.validator.Author;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class BookDTO {
    private Long id;

    @NotEmpty(message = "Please provide a name")
    private String name;

    @Author
    private String author;

    @NotNull(message = "Please provide a price")
    @DecimalMin("1.00")
    private BigDecimal price;
}
