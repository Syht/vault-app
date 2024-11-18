package com.syht.vaultapp.web.rest;

import com.syht.vaultapp.api.controller.BookApi;
import com.syht.vaultapp.api.model.BookDTO;
import com.syht.vaultapp.service.BookService;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;

public class BookResource implements BookApi {

    private final BookService bookService;

    public BookResource(final BookService pBookService) {
        bookService = pBookService;
    }

    /**
     * POST /book : Create a new book
     * Adds a new book to the database.
     *
     * @param pBookDTO Book object to be created (required)
     * @return Book created successfully (status code 201)
     * or Invalid book data (status code 400)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<BookDTO> createBook(final BookDTO pBookDTO) {
        final BookDTO bookDTO = bookService.createBook(pBookDTO);
        return ResponseEntity.created(URI.create("/book/" + bookDTO.getId())).body(bookDTO);
    }

    /**
     * DELETE /book/{id} : Delete an book
     * Deletes an book by its unique identifier.
     *
     * @param id The ID of the book to delete (required)
     * @return Book deleted successfully (status code 204)
     * or Book not found (status code 404)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<Void> deleteBook(final Integer id) {
        if (bookService.deleteBook(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * GET /book : Get all book
     * Retrieve a list of all book in the database, with optional pagination.
     *
     * @param page Page number for pagination (optional, default to 0)
     * @param size Page size for pagination (optional, default to 10)
     * @return List of book (status code 200)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<List<BookDTO>> getAllBooks(final Integer page, final Integer size) {
        return ResponseEntity.ok(this.bookService.getAllBook(page, size));
    }

    /**
     * GET /book/{id} : Get a specific book by ID
     * Retrieve a specific book by its unique identifier.
     *
     * @param id The ID of the book to retrieve (required)
     * @return The book object (status code 200)
     * or Book not found (status code 404)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<BookDTO> getBookById(final Integer id) {
        final BookDTO bookDTO = this.bookService.getBookById(id);
        if (bookDTO != null) {
            return ResponseEntity.ok(bookDTO);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * PUT /book/{id} : Update an book
     * Update the details of an existing book.
     *
     * @param id       The ID of the book to update (required)
     * @param pBookDTO Updated book object (required)
     * @return Book updated successfully (status code 200)
     * or Invalid book data (status code 400)
     * or Book not found (status code 404)
     * or Internal server error (status code 500)
     */
    @Override
    public ResponseEntity<BookDTO> updateBook(final Integer id, final BookDTO pBookDTO) {
        final BookDTO bookDTO = this.bookService.updateBook(id, pBookDTO);
        if (bookDTO != null) {
            return ResponseEntity.ok(bookDTO);
        }
        return ResponseEntity.notFound().build();
    }
}
