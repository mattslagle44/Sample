package com.example.todo.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Immutable DTO used for both requests and responses
public record TodoItem(
        Long id,
        @NotBlank @Size(max = 120) String title,
        @Size(max = 500) String notes,
        boolean done
) {
    // convenience factory for new items (no id yet)
    public static TodoItem newItem(String title, String notes) {
        return new TodoItem(null, title, notes, false);
    }
}
