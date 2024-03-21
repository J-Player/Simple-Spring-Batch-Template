package com.example.processors;

import com.example.entities.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class UserProcessor implements ItemProcessor<User, User> {

    @Override
    public User process(final User user) {
        //Business logic here
        return user;
    }

}
