package com.blogging.app.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class CategoryDto {

    private Integer categoryId;
    @NotBlank
    @Size(min=4,message = "minimum size should be 4 characters")

    private String categoryTitle;
    @NotBlank
    @Size(min=10,message = "minimum size should be 10 characters")
    private String categoryDescription;
}

