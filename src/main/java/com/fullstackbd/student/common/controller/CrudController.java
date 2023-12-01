package com.fullstackbd.student.common.controller;

import com.fullstackbd.student.common.exceptions.DatabaseAddException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CrudController v1.0
 * CrudController is an interface that contains the basic CRUD operations. It is a generic interface that can be used for any entity.
 *
 * @param <E>
 * @param <I>
 */
public interface CrudController<E, D, I> {

    /**
     * Get The List of Entity <E>
     *
     * @method GET
     * @status_code 200 | OK
     * @return List<E>
     */
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    List<E> getAll();

    /**
     * Get The Specific Entity <E> by ID <I>
     *
     * @method GET
     * @status_code 200 | OK or 404 | NOT FOUND
     * @return ResponseEntity<E> or ResponseEntity<Error>
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    E getById(@PathVariable("id") I id);
    /**
     * Save The Entity <E>
     *
     * @method POST
     * @status_code 201 | CREATED or 400 | BAD REQUEST
     * @return ResponseEntity<E> or ResponseEntity<Error>
     */
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    E save(@Valid @RequestBody D dto) throws DatabaseAddException;
    /**
     * Update The Entity <E> by ID <I>
     *
     * @method PUT or PATCH
     * @status_code 200 | OK or 400 | BAD REQUEST
     * @return ResponseEntity<E> or ResponseEntity<Error>
     */
    @PutMapping("/{id}")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    E update(@PathVariable("id") Long id, @Valid @RequestBody D dto);
    /**
     * Delete The Entity <E> by ID <I>
     *
     * @method DELETE
     * @status_code 200 | OK or 400 | BAD REQUEST or 404 | NOT FOUND
     * @return ResponseEntity<?>
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    E deleteById(@PathVariable("id") I id);
}
