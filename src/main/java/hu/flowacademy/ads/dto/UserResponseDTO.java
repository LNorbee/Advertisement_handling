package hu.flowacademy.ads.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserResponseDTO {

    private String userName;
    private String fullName;
    private LocalDate creationDate;

}
