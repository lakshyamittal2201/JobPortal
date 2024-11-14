package com.jobportalapp.jobportal.services;

import com.jobportalapp.jobportal.entity.UsersType;
import com.jobportalapp.jobportal.repository.UsersTypeRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersTypeService {

    private final UsersTypeRespository usersTypeRespository;

    @Autowired
    public UsersTypeService(UsersTypeRespository usersTypeRespository) {
        this.usersTypeRespository = usersTypeRespository;
    }

    public List<UsersType> getAll() {
        return usersTypeRespository.findAll();
    }

}
