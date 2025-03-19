package org.example.libraryservice.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Book {

    @Id
    private int id;
    private LocalDate takenDate;
    private LocalDate returnDate;

}
