package de.mgr.example;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserRepository {

    public static Map<String, User> fakeUserRepository = new HashMap<String, User>();

    public User findByLogin(String userid) {
        return fakeUserRepository.get(userid);
    }




}