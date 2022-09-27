package com.reading.reading.model.customer;

import com.reading.reading.api.response.customer.CustomerDTO;
import com.reading.reading.enums.Status;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;

@Entity
@Data
@Audited
public class Customer {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    @Size(min = 10, max = 13)
    @NotNull
    private String phone;

    @NotNull
    private String password;

    @NotNull
    private String fullName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    private OffsetDateTime createdDate;

    public CustomerDTO toDTO() {
        return new ModelMapper().map(this, CustomerDTO.class);
    }

}
