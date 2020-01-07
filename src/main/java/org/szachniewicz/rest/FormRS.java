package org.szachniewicz.rest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/form")
@CrossOrigin(origins = "*")
public class FormRS {

    @GetMapping(path = "formsToEdit")
    public String formsToEdit() {
        return "{\"formsList\":[{\"name\": \"test1\" ,\"dateOfEdit\": \"2019-07-11\"}, {\"name\": \"test2\" ,  \"dateOfEdit\": \"2018-07-11\"}]}";
    }

    @PostMapping(path = "saveForm")
    public String saveForm(@RequestBody String jsonStr) {
        return "{\"succes\":\"sucess\"}";
    }

}
