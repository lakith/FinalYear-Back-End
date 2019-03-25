package com.finalproj.finalproject.dto;

import com.finalproj.finalproject.model.EventForm;

public class FormConfigDTO {

    private String elementName;
    private String elementType;
    private String elementConfigType;
    private String placeHolderText;
    private boolean required;
    private String errorMessage;
    private boolean radio;
    private int radioGroup;

    public FormConfigDTO() {
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getElementType() {
        return elementType;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType;
    }

    public String getElementConfigType() {
        return elementConfigType;
    }

    public void setElementConfigType(String elementConfigType) {
        this.elementConfigType = elementConfigType;
    }

    public String getPlaceHolderText() {
        return placeHolderText;
    }

    public void setPlaceHolderText(String placeHolderText) {
        this.placeHolderText = placeHolderText;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isRadio() {
        return radio;
    }

    public void setRadio(boolean radio) {
        this.radio = radio;
    }

    public int getRadioGroup() {
        return radioGroup;
    }

    public void setRadioGroup(int radioGroup) {
        this.radioGroup = radioGroup;
    }
}
