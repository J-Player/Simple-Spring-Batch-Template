package com.example.configs;

import java.beans.PropertyEditorSupport;
import java.time.Instant;

public class InstantPropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(Instant.parse(text));
    }

    @Override
    public String getAsText() {
        return ((Instant) getValue()).toString();
    }

}
