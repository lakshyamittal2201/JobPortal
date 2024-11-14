package com.jobportalapp.jobportal.services;

import com.jobportalapp.jobportal.entity.RecruiterProfile;
import com.jobportalapp.jobportal.entity.Users;
import com.jobportalapp.jobportal.repository.RecruiterProfileRespository;
import com.jobportalapp.jobportal.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecruiterProfileService {

    private final RecruiterProfileRespository recruiterProfileRespository;
    private final UsersRepository usersRepository;

    @Autowired
    public RecruiterProfileService(RecruiterProfileRespository recruiterProfileRespository, UsersRepository usersRepository) {
        this.recruiterProfileRespository = recruiterProfileRespository;
        this.usersRepository = usersRepository;
    }

    public Optional<RecruiterProfile> getOne(Integer id) {
        return recruiterProfileRespository.findById(id);
    }

    public RecruiterProfile addNew(RecruiterProfile recruiterProfile) {
        return recruiterProfileRespository.save(recruiterProfile);
    }

    public RecruiterProfile getCurrentRecruiterProfile() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            Users users = usersRepository.findByEmail(currentUsername).orElseThrow(() -> new UsernameNotFoundException(
                    "User not found"
            ));
            Optional<RecruiterProfile> recruiterProfile = getOne(users.getUserId());
            return recruiterProfile.orElse(null);
        } else return null;

    }
}
