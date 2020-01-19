package org.szachniewicz.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.szachniewicz.model.FormInfo;
import org.szachniewicz.model.FormSchema;
import org.szachniewicz.servises.FormService;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/forms")
@CrossOrigin(origins = "*")
public class FormRS {

    private final FormService formService;

    public FormRS(FormService formService) {
        this.formService = formService;
    }

    @GetMapping(path = "formList")
    public List<FormInfo> getFormList() {
        return formService.getFormList();
    }

    @PostMapping(path = "formSchema")
    public String postEditForm(@RequestBody FormSchema formSchema) {
        formService.saveFormEdit(formSchema);
        return "{\"succes\":\"sucess\"}";
    }

    @GetMapping(path = "formSchema/{id}")
    public FormSchema getForm(@PathVariable BigDecimal id) {
        return formService.getFormSchema(id);
    }

    @PostMapping(path = "form/{id}")
    public String saveForm(@RequestBody String form, @PathVariable BigDecimal id) {
        formService.saveFormFill(id, form);
        return "{\"succes\":\"sucess\"}";
    }

    @GetMapping(path = "test")
    public List<test> test() {
        List<test> tests = new LinkedList<>();
        test a = new test();
        a.setA("a");
        a.setB("ab");
        tests.add(a);
        test b = new test();
        b.setA("ba");
        b.setB("bab");
        tests.add(b);
        test c = new test();
        c.setA("cba");
        c.setB("cbab");
        tests.add(c);
        return tests;
    }

    public class test {
        private String a;
        private String b;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }
    }


}
