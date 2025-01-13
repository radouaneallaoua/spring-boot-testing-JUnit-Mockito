package net.allaoua.customerservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class CustomerDTO {

    private Long id;
    @NotEmpty
    @Size(min = 2)
    private String firstName;
    @NotEmpty
    @Size(min = 2)
    private String lastName;
    @NotEmpty
    @Size(min = 8)
    private String email;
}
