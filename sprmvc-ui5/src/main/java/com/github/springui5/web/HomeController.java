package com.github.springui5.web;

import com.github.springui5.domain.User;
import com.github.springui5.model.HomeModel;
import com.github.springui5.model.HomeViewModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller for handling Ajax requests from {@code home.controller.js}. Will be picked up during the
 * component scan, see {@linkplain org.springframework.context.annotation.ComponentScan} annotation specification on
 * {@linkplain com.github.springui5.conf.WebAppConfigurer}.
 */
@Controller
@RequestMapping(value = "/home", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    /**
     * Session-scoped view-model bean.
     */
    @Autowired
    private HomeViewModel vm;

    /**
     * Initializes the model for the view.
     */
    @RequestMapping
    public
    @ResponseBody
    HomeModel handleInit() {
        return vm.getNewHomeModel().show();
    }

    /**
     * Adds the {@linkplain com.github.springui5.domain.User} parsed from the request body to the list of users in the
     * model.
     */
    @RequestMapping("/add")
    public
    @ResponseBody
    HomeModel handleAdd(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new UserValidationException(errors);
        }
        return vm.getHomeModel().add(user).clearError().show();
    }

    /**
     * Deletes the the {@linkplain com.github.springui5.domain.User} with matching {@code id} from the list of users in
     * the model.
     */
    @RequestMapping("/delete/{id}")
    public
    @ResponseBody
    HomeModel handleDelete(@PathVariable long id) {
        return vm.getHomeModel().delete(id).clearError().show();
    }

    /**
     * Updates the the {@linkplain com.github.springui5.domain.User} with matching {@code id} from the list of users in
     * the model.
     */
    @RequestMapping("/update")
    public
    @ResponseBody
    HomeModel handleUpdate(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new UserValidationException(errors);
        }
        return vm.getHomeModel().update(user).clearError().show();
    }

    /**
     * Custom exception handler for {@linkplain UserValidationException} exceptions which produces a response with the
     * status {@linkplain HttpStatus#BAD_REQUEST} and the body string which contains the reason for the first field
     * error.
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public
    @ResponseBody
    HomeModel handleException(UserValidationException ex) {
        String error = String.format("%s %s", ex.getRejectedField(), ex.getRejectedMessage());
        logger.debug("Validation error: {}", error);
        return vm.getHomeModel().storeError(error);
    }

}
