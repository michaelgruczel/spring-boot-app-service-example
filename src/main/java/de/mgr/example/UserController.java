package de.mgr.example;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class UserController {

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer access_token", required = true, dataType = "string", paramType = "header"),
    })
    @RequestMapping(method = RequestMethod.GET, value = "/user", produces = APPLICATION_JSON_VALUE)
    public User getUser() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User aUser = UserRepository.fakeUserRepository.get(auth.getName());
        if(auth != null && aUser != null) {
            return aUser;
        } else {
            throw new IllegalArgumentException("error.username");
        }
    }

    @ExceptionHandler
    void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

}