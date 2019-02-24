package com.atwalg2.basic.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.ConstraintViolationException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    private String[] basicUserFilter = {
        "email",
        "givenName",
        "surname",
        "countryCode"
    };

    @Autowired
    UserRepository repository;

    /** Get all users */
    @RequestMapping
    public MappingJacksonValue handleAllUsersRequest(){
        List<UserModel> users = repository.findAll();

        if (users == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(this.basicUserFilter);
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserRepositoryFilter", filter);

        MappingJacksonValue mapJack = new MappingJacksonValue(users);
        mapJack.setFilters(filters);
        return mapJack;
    }

    /** Get a user */
    @GetMapping("/{userId}")
    public MappingJacksonValue getUser(@PathVariable("userId") Long userId) {
        Optional<UserModel> user = repository.findById(userId);

        if (!user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(this.basicUserFilter);
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserRepositoryFilter", filter);

        MappingJacksonValue mapJack = new MappingJacksonValue(user.get());
        mapJack.setFilters(filters);
        return mapJack;
    }

    /** Create a new user. */
    @PostMapping
    public MappingJacksonValue createUser(@RequestBody UserModel user) throws Exception {
        try {
            UserModel userModel = repository.save(user);

            SimpleFilterProvider filters = new SimpleFilterProvider();
            filters.setFailOnUnknownId(false);

            MappingJacksonValue mapJack = new MappingJacksonValue(userModel);
            mapJack.setFilters(filters);
            return mapJack;

        } catch (ConstraintViolationException e) {
            //DataIsNotValidException is our custom exception
            throw new Exception("Data is not valid" + e.getMessage());
        }
    }
}
