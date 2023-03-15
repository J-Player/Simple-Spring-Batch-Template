package com.example.configs;

import io.micrometer.common.util.StringUtils;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.stereotype.Component;
import org.springframework.validation.DataBinder;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class BeanWrapperFieldSetMapperCustom<T> extends BeanWrapperFieldSetMapper<T> {

    @Override
    protected void initBinder(DataBinder binder) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(StringUtils.isNotEmpty(text) ? LocalDate.parse(text, formatter) : null);
            }
            @Override
            public String getAsText() throws IllegalArgumentException {
                Object date = getValue();
                if (date != null)
                    return formatter.format((LocalDate) date);
                else
                    return "";
            }
        });
    }
}
