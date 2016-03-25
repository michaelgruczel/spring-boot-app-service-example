package de.mgr.example;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class RegistrationController {

    @RequestMapping(method = RequestMethod.POST, value = "/register", produces = APPLICATION_JSON_VALUE)
    public User register(@RequestBody User user) {

        if(user.getMail() == null) {
            throw new IllegalArgumentException("error.username");
        } else {
            // ugly put for this demo ok
            UserRepository.fakeUserRepository.put(user.getMail(), user);
        }

        return user;
    }

    @ExceptionHandler
    void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

}
