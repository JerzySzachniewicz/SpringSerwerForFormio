package org.szachniewicz.servises;

import org.springframework.stereotype.Service;
import org.szachniewicz.model.FormInfo;
import org.szachniewicz.model.FormSchema;

import javax.annotation.PostConstruct;
import java.io.*;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

@Service
public class FormFileManager {

    public static final String FORM_INFO_PATH = "/formInfo";
    public static final String FORM_SCHEMA_PATH = "/formSchema";
    public static final String FILLED_FORMS_PATH = "/filledForms";
    private Path formInfoDirectory;
    private Path formSchemaDirectory;
    private Path filledFormsDirectory;

    @PostConstruct
    public void init(){
        createDictionaries();
    }

    private void createDictionaries() {
        try {
            String jarFilePatch = "C:/";
            createFormInfoDir(jarFilePatch);
            createFormSchemaDir(jarFilePatch);
            createFilledFormsDir(jarFilePatch);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createFilledFormsDir(String jarFilePatch) throws IOException {
        Path filledFormDir = Paths.get(jarFilePatch + FILLED_FORMS_PATH);
        if (!Files.exists(filledFormDir)) {
            filledFormsDirectory = Files.createDirectory(Paths.get(jarFilePatch + FILLED_FORMS_PATH));
        } else {
            filledFormsDirectory = filledFormDir;
        }
    }

    private void createFormSchemaDir(String jarFilePatch) throws IOException {
        Path formSchemaDir = Paths.get(jarFilePatch + FORM_SCHEMA_PATH);
        if (!Files.exists(formSchemaDir)) {
            formSchemaDirectory = Files.createDirectory(formSchemaDir);
        } else {
            formSchemaDirectory = formSchemaDir;
        }
    }

    private void createFormInfoDir(String jarFilePatch) throws IOException {
        Path formInfoDir = Paths.get(jarFilePatch + FORM_INFO_PATH);
        if (!Files.exists(formInfoDir)) {
            formInfoDirectory = Files.createDirectory(formInfoDir);
        } else {
            formInfoDirectory = formInfoDir;
        }
    }

    public List<FormInfo> getFormList() {
        List<FormInfo> results = new LinkedList<>();
        File[] files = new File(formInfoDirectory.toUri()).listFiles();
        getFormInfosFromFiles(results, files);
        return results;
    }

    private void getFormInfosFromFiles(List<FormInfo> results, File[] files) {
        for (File file : files) {
            getFromInfoFromFile(results, file);
        }
    }

    private void getFromInfoFromFile(List<FormInfo> results, File file) {
        if (file.isFile()) {
            try (FileInputStream fi = new FileInputStream(file);
                 ObjectInputStream oi = new ObjectInputStream(fi)) {
                FormInfo formInfo = (FormInfo) oi.readObject();
                results.add(formInfo);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public FormSchema getFormSchema(BigDecimal formId) {
        File file = new File(formSchemaDirectory.toString()+ "/" + formId.toString() + ".txt");
        try (FileInputStream f = new FileInputStream(file);
             ObjectInputStream o = new ObjectInputStream(f)) {
            return (FormSchema) o.readObject();
        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new FormSchema();
    }

    public void saveFormSchema(FormSchema formSchema, BigDecimal id) {
        File file = new File(formSchemaDirectory.toString()+ "/" + id.toString() + ".txt");
        try (FileOutputStream f = new FileOutputStream(file);
             ObjectOutputStream o = new ObjectOutputStream(f)) {
            o.writeObject(formSchema);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveFormInfo(FormInfo formInfo) {
        File file = new File(formInfoDirectory.toString()+ "/" + formInfo.getId().toString() + ".txt");
        try (FileOutputStream f = new FileOutputStream(file);
             ObjectOutputStream o = new ObjectOutputStream(f)) {
            o.writeObject(formInfo);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveFilledForm(BigDecimal formId, String content) {

    }

    public BigDecimal getFreeFormId() {
//        File[] files = new File(formInfoDirectory.toUri()).listFiles();
//        return new BigDecimal(files.length);
        return BigDecimal.ZERO;
    }
}
