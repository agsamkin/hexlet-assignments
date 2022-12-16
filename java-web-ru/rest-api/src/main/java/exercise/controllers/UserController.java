package exercise.controllers;

import io.javalin.core.validation.BodyValidator;
import io.javalin.core.validation.JavalinValidation;
import io.javalin.core.validation.ValidationError;
import io.javalin.core.validation.Validator;
import io.javalin.http.Context;
import io.javalin.apibuilder.CrudHandler;
import io.ebean.DB;
import java.util.List;
import java.util.Map;

import exercise.domain.User;
import exercise.domain.query.QUser;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

public class UserController implements CrudHandler {

    public void getAll(Context ctx) {
        // BEGIN
        List<User> users = new QUser()
            .orderBy()
            .id.asc()
            .findList();
        ctx.attribute("users", users);

        String json = DB.json().toJson(users);
        ctx.json(json);
        // END
    };

    public void getOne(Context ctx, String id) {

        // BEGIN
        User user = new QUser()
                .id.equalTo(Integer.parseInt(id))
                .findOne();
        ctx.attribute("user", user);

        String json = DB.json().toJson(user);
        ctx.json(json);
        // END
    };

    public void create(Context ctx) {
        // BEGIN
        String body = ctx.body();
        User user = DB.json().toBean(User.class, body);

        BodyValidator<User> userBodyValidator =  ctx.bodyValidator(User.class);

        userBodyValidator
               .check(obj -> !obj.getFirstName().isEmpty(), "Имя не должно быть пустым")
               .check(obj -> !obj.getLastName().isEmpty(), "Фамилия не должна быть пустой")
               .check(obj -> EmailValidator.getInstance().isValid(obj.getEmail()), "Невалидный e-mail")
               .check(obj -> obj.getPassword().length() >=4, "Password должен быть не короче 4 символов")
               .check(obj -> StringUtils.isNumeric(obj.getPassword()), "Password должен содержать только цифры")
               .get();

        Map<String, List<ValidationError<User>>> errors = userBodyValidator.errors();
        user.save();

//        String json = DB.json().toJson(user);
//        ctx.json(json);
        // END
    };

    public void update(Context ctx, String id) {
        // BEGIN
        String body = ctx.body();
        User user = DB.json().toBean(User.class, body);
        user.setId(id);
        user.update();

//        String json = DB.json().toJson(user);
//        ctx.json(json);
        // END
    };

    public void delete(Context ctx, String id) {
        // BEGIN
        User user = new QUser()
                .id.equalTo(Integer.parseInt(id))
                .findOne();
        user.delete();

        // END
    };
}
