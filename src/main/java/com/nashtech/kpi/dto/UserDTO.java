package com.nashtech.kpi.dto;

import com.nashtech.kpi.model.Provider;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class UserDTO {
    Integer id;
    String username;
    String googleId;
    String email;
    List<String> authorities;
    Provider provider;
    Boolean enabled;


    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", authorities=" + authorities +
                ", provider=" + provider +
                ", enabled=" + enabled +
                '}';
    }

}
