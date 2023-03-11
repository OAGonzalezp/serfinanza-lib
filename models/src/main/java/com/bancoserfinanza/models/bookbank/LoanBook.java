package com.bancoserfinanza.models.bookbank;

import com.bancoserfinanza.models.enums.BookStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "BK_TakenBooks")
public class LoanBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Customer customer;

    @Column(name = "takenDate", nullable = false)
    private Date loanDate;

    @Column(name = "deliveryDate", nullable = false)
    private Date deliveryDate;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

}
