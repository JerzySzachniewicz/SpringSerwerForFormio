package org.szachniewicz.servises;

import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.szachniewicz.model.FormInfo;
import org.szachniewicz.model.FormSchema;

import java.io.FileReader;
import java.util.List;
import java.util.Objects;

@Service
public class FormService {

    private final FormFileManager formFileManager;

    public FormService(FormFileManager formFileManager) {
        this.formFileManager = formFileManager;
    }

    public List<FormInfo> getFormList() {
        return null;
    }

    public void saveFormEdit(FormSchema formSchema) {

    }

    public FormSchema getFormSchema(String formId) {
        JSONParser parser = new JSONParser();
        FormSchema formSchema = new FormSchema();

        String file = Objects.requireNonNull(FormService.class.getClassLoader().getResource("form.json")).getFile();
        try {
            formSchema.setComponents(parser.parse(new FileReader(file)).toString());
        } catch (Exception ignored){

        }

        return formSchema;
    }

    public void saveFormFill(String id, String form) {

    }
}
