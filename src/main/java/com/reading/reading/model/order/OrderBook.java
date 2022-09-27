package com.reading.reading.model.order;

import com.reading.reading.model.book.Book;
import com.reading.reading.model.customer.Customer;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Data
@Audited
public class OrderBook {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    private Book book;

    @ManyToOne(optional = false)
    private Customer customer;

    @NotNull
    private String basketReference;

    @NotNull
    private BigDecimal amount;

    @Min(1)
    private int quantity;
}
