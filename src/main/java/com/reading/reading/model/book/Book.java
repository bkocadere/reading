package com.reading.reading.model.book;

import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Data
@Audited
public class Book {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(unique = true)
    @NotNull
    private String reference;

    @NotNull
    private BigDecimal amount;

    /*@OneToOne
    @JoinColumn(name = "book_stock_id")
    private BookStock bookStock;*/

}
