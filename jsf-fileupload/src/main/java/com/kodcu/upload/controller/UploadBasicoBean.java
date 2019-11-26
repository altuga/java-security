package com.kodcu.upload.controller;

import com.kodcu.upload.util.UtilityFile;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class UploadBasicoBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
           
    private List<File> fileArrayList = new ArrayList<>();
    
    private UploadedFile uploadedFile;
    
    @PostConstruct
    public void postConstruct() {
        fileArrayList = new ArrayList<>(UtilityFile.toList());
    }
    
    public void upload() {
        try {


            // TODO 1
            // TODO 2 : don't accept html file

            File file = UtilityFile.write(uploadedFile.getFileName(), uploadedFile.getContents());

            
            fileArrayList.add(file);
            
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Upload completed", "O file " + file.getName() + " has been saved!"));
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));
        }
    }
    
    public List<File> getFileArrayList() {
        return fileArrayList;
    }
    
    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }
    
    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
}