package com.vincent.demo.Auth;

import com.vincent.demo.entity.app_user.AppUser;
import com.vincent.demo.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SpringUserService implements UserDetailsService {

    @Autowired
    private AppUserService appUserSer;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        try{
            AppUser appUser = appUserSer.getUserByEmail(s);
            return new SpringUser(appUser);
        }catch(UsernameNotFoundException e){
            throw new UsernameNotFoundException("id is not found");
        }
    }
}
