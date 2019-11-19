package com.algaworks.upload.controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.algaworks.upload.util.UtilityFile;

@ManagedBean
@ViewScoped
public class UploadAdvancedBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private List<File> files = new ArrayList<>();
    
    @PostConstruct
    public void postConstruct() {
        files = new ArrayList<>(UtilityFile.toList());
    }
    
    public void upload(FileUploadEvent event) {
        UploadedFile uploadedFile = event.getFile();
        
        try {
            File file = UtilityFile.write(uploadedFile.getFileName(), uploadedFile.getContents());
            
            files.add(file);
            
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Upload completed", "O files " + file.getName() + "  has been saved"));
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", e.getMessage()));
        }
    }
    
    public List<File> getFiles() {
        return files;
    }
}