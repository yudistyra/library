package mitrais.yudis.library.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	@GetMapping("/hello-world/{title}")
    public String helloWorld(@PathVariable String title){
        return "Hello World " + title;
    }
}
