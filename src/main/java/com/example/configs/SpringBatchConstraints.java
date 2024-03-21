package com.example.configs;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class SpringBatchConstraints {
    protected static final String[] ITEM_READER_NAMES = {"id", "username", "password", "email", "nationality", "birthDate"};
    public static final String JOB_NAME = "job";
    public static final String STEP_NAME = "csv-step";
}
