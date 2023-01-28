package exercise;

import exercise.daytimes.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
class WelcomeController {

    @Autowired
    Daytime daytime;

    @Autowired
    Meal meal;

    @GetMapping(path = "/daytime")
    public String greet() {
        return "It is " + daytime.getName()
                + " now. Enjoy your "
                + meal.getMealForDaytime(daytime.getName());
    }

}
// END
