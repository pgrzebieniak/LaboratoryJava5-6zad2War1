package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import java.util.List;

@Controller
public class WebController extends WebMvcConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static List<PersonForm> personList;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/uploadForm").setViewName("uploadForm");
    }

    @GetMapping("/")
    public String showForm(PersonForm personForm) {
        return "form";
    }

    @PostMapping("/")
    public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        insertToDatabase(personForm);
        return "redirect:/uploadForm";
    }

    public void insertToDatabase(PersonForm person) {
        log.info("Creating tables");

        jdbcTemplate.execute("DROP TABLE persons IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE persons(" +
                "id SERIAL, the_name VARCHAR(255), lastName VARCHAR(255), age INTEGER , nationality VARCHAR(255)," +
                " posiotion VARCHAR(255))");

        log.info(String.format("Inserting customer record for %s %s %s %s", person.getName(),person.getLastName(),
                person.getAge(), person.getNationality(),person.getPosition()));

        jdbcTemplate.update("INSERT INTO persons(the_name,lastName, age, nationality,position) VALUES (?,?,?,?)",
                new Object[]{person.getName(),person.getLastName(), person.getAge(), person.getNationality(),person.posiotion});
    }
}