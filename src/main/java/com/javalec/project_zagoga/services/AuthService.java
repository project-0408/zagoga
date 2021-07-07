package com.javalec.project_zagoga.services;

import com.javalec.project_zagoga.mapper.AuthMapper;
import com.javalec.project_zagoga.mapper.HostMapper;
import com.javalec.project_zagoga.mapper.UsersMapper;
import com.javalec.project_zagoga.security.AuthValue;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {

    private final AuthMapper authMapper;
    private final UsersMapper usersMapper;
    private final HostMapper hostMapper;
    private final PasswordEncoder passwordEncoder;

    public int findPWByEmail(String jumin, String username, String name){
        int isFind = 0;
        AuthValue authValue = authMapper.loadUserByName(username);
        if(authValue == null) {
            return 0;
        }

        switch (authValue.getRole()){
            case "USER":
                isFind = usersMapper.loadForFindWD(authValue, name, jumin);
                break;
            case "HOST":
                isFind = hostMapper.loadForFindWD(authValue, name, jumin);
                break;
        }

        if (isFind != 0){
            isFind = authValue.getSc_no();
        }
        return isFind;
    }

    public void updatePW(int sc_no, String password) {
        String encPwd = passwordEncoder.encode(password);
        authMapper.updatePW(sc_no, encPwd);
    }
}