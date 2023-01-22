package exercise.controller;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import exercise.model.User;
import exercise.model.QUser;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Зависимости для самостоятельной работы
// import org.springframework.data.querydsl.binding.QuerydslPredicate;
// import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    // BEGIN
//    @GetMapping(path = "")
//    public Iterable<User> getAll(@RequestParam(required = false) String firstName
//            , @RequestParam(required = false) String lastName) {
//
//        if (Objects.nonNull(firstName) && Objects.nonNull(lastName)) {
//            return userRepository.findAll(QUser.user.firstName.containsIgnoreCase(firstName)
//                    .and(QUser.user.lastName.containsIgnoreCase(lastName)));
//        } else if (Objects.nonNull(firstName) ) {
//            return userRepository.findAll(QUser.user.firstName.containsIgnoreCase(firstName));
//        } else if (Objects.nonNull(lastName)) {
//            return userRepository.findAll(QUser.user.lastName.containsIgnoreCase(lastName));
//        } else {
//            return userRepository.findAll();
//        }
//    }

    @GetMapping(path = "")
    public Iterable<User> getAll(@QuerydslPredicate(root = User.class) Predicate predicate) {
        return userRepository.findAll(predicate);
    }

    // END
}

