package com.reading.reading.model.book;

import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Data
@Audited
public class BookStock {
    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "book_id")
    private Book book;

    @Min(0)
    private int quantity;

}
