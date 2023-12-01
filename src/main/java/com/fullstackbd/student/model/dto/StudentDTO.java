package com.fullstackbd.student.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Class is mandatory")
    @Positive(message = "Class must be positive value")
    @Max(value = 12, message = "Class must be less than 12")
    @JsonProperty("class")
    private Integer className;

    @NotNull(message = "Roll is mandatory")
    @Positive(message = "Roll must be positive value")
    private Integer roll;

    @NotBlank(message = "Section is mandatory")
    private String section;
}