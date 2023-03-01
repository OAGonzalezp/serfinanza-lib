package com.bancoserfinanza.models.bookbank;

import com.bancoserfinanza.models.enums.BookStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "BK_Book")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "idlib", nullable = false)
    private String idlib;
    @Column(name = "bookName", nullable = false)
    private String bookName;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TakenBooks> takenBooks;

    @Column(name = "lastTakenDate")
    private Date lastTakenDate;
}
