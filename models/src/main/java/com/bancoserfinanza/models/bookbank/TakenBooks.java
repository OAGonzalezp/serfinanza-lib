package com.bancoserfinanza.models.bookbank;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "BK_TakenBooks")
public class TakenBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Customer customer;

    @Column(name = "takenDate", nullable = false)
    private Date takenDate;

    @Column(name = "deliveryDate", nullable = false)
    private Date deliveryDate;

}
