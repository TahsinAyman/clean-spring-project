package com.fullstackbd.student.model.dto;

import java.util.List;
import java.util.Map;

import com.fullstackbd.student.model.constants.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidationError {
    @Enumerated(EnumType.STRING)
    private Status status;
    private String message;
    private Map<String, List<String>> errors;
}
