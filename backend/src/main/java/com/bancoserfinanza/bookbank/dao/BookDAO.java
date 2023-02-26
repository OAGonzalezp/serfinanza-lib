package com.bancoserfinanza.bookbank.dao;

import com.bancoserfinanza.models.bookbank.Book;
import com.bancoserfinanza.models.request.BookRequest;

public interface BookDAO {

    Book save(BookRequest bookRequest);
}
