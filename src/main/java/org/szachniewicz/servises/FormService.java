package org.szachniewicz.servises;

import org.springframework.stereotype.Service;
import org.szachniewicz.model.FormInfo;
import org.szachniewicz.model.FormSchema;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class FormService {

    private final FormFileManager formFileManager;

    public FormService(FormFileManager formFileManager) {
        this.formFileManager = formFileManager;
    }

    public List<FormInfo> getFormList() {
        return formFileManager.getFormList();
    }

    public void saveFormEdit(FormSchema formSchema) {
        BigDecimal id = formFileManager.getFreeFormId();
        generateAndSaveFormInfo(formSchema, id);
        formFileManager.saveFormSchema(formSchema, id);
    }

    private void generateAndSaveFormInfo(FormSchema formSchema, BigDecimal id) {
        FormInfo formInfo = new FormInfo();
        formInfo.setId(id);
        formInfo.setCreationDate(new Date());
        formInfo.setModificationDate(new Date());
        formInfo.setName(formSchema.getName());
        formInfo.setTitle(formSchema.getTitle());
        formInfo.setPath(formInfo.getPath());
        formFileManager.saveFormInfo(formInfo);
    }

    public FormSchema getFormSchema(BigDecimal formId) {
        return formFileManager.getFormSchema(formId);
    }

    public void saveFormFill(BigDecimal id, String form) {
        formFileManager.saveFilledForm(id, form);
    }
}
