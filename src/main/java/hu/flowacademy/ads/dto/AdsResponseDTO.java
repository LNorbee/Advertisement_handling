package hu.flowacademy.ads.dto;

import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;

@Data
public class AdsResponseDTO {

    private Long id;
    private String title;
    private Integer price;
    private String description;
    private LocalDate creationDate;
    private String userName;

}
