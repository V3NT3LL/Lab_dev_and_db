package com.example.Lab1;

import java.time.LocalDateTime;

public class Document {
    private String name;
    private DocType docType;
    private String body;
    private LocalDateTime creationDate;
    private LocalDateTime signingDate;
    private String userLogin;

    public Document(String name, DocType docType, String body, LocalDateTime creationDate,
                    LocalDateTime signingDate, String userLogin) {
        this.name = name;
        this.docType = docType;
        this.body = body;
        this.creationDate = creationDate;
        this.signingDate = signingDate;
        this.userLogin = userLogin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DocType getDocType() {
        return docType;
    }

    public void setDocType(DocType docType) {
        this.docType = docType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getSigningDate() {
        return signingDate;
    }

    public void setSigningDate(LocalDateTime signingDate) {
        this.signingDate = signingDate;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
}
