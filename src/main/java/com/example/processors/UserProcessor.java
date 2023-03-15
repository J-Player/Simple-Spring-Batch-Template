package com.example.processors;

import com.example.entities.User;
import org.springframework.batch.item.ItemProcessor;

public class UserProcessor implements ItemProcessor<User, User> {

    @Override
    public User process(User item) {
        return null;
    }

}
