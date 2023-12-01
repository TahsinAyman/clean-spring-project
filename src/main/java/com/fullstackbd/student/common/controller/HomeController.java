package com.fullstackbd.student.common.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This is a generic interface for home controller
 *
 * @param <M>
 */
public interface HomeController<M> {
    /**
     * Get the home message
     *
     * @method GET
     * @status_code 200 | OK
     * @return M
     */
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    M home();
}
