package com.nashtech.kpi.service;

import com.nashtech.kpi.dto.UserDTO;
import com.nashtech.kpi.entity.UserEntity;
import com.nashtech.kpi.model.ERole;
import com.nashtech.kpi.repository.RoleRepository;
import com.nashtech.kpi.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;


    public void saveGoogleAuthenticationUser(OAuth2User oAuth2User) {
        Collection<? extends GrantedAuthority> authorities = oAuth2User.getAuthorities();

        Optional<? extends GrantedAuthority> grantedAuthority = authorities.stream().findFirst();

        UserEntity userEntity = new UserEntity();
        if (grantedAuthority.isPresent()) {
            Map<String, Object> attributes = ((OidcUserAuthority) grantedAuthority.get()).getAttributes();
            String email = String.valueOf(attributes.get("email"));
            userEntity.setGoogleId(oAuth2User.getName());
            userEntity.setUsername(email.split("@")[0]);
            userEntity.setEmail(email);
            userRepository.save(userEntity);
        }
    }

    public List<UserDTO> findActiveUsers() {
        List<UserEntity> findByEnabled = userRepository.findByEnabled(true);
        return modelMapper.map(findByEnabled, new TypeToken<List<UserDTO>>() {}.getType());
    }

    public UserDTO findById(int id) {
        Optional<UserEntity> byId = userRepository.findById(id);
        return modelMapper.map(byId, new TypeToken<List<UserDTO>>() {}.getType());
    }


    public UserDTO saveUser(UserDTO dto) {
        UserEntity entity;
        if(dto.getId() == null || userRepository.findById(dto.getId()).isEmpty()) {
            entity = modelMapper.map(dto, UserEntity.class);
            roleRepository.findByRole(ERole.CLIENT_USER.name()).ifPresent(roleUser -> entity.setAuthorities(Collections.singleton(roleUser)));
            entity.setCreatedDate(LocalDateTime.now());
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication != null) {
                entity.setCreatedBy(authentication.getName());
            }
        }
        else {
            entity = userRepository.findById(dto.getId()).get();
//            entity.setUsername(dto.getUsername());
            entity.setProvider(dto.getProvider());
            entity.setLastUpdatedDate(LocalDateTime.now());
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication != null) {
                entity.setLastUpdatedBy(authentication.getName());
            }
        }
        return modelMapper.map(userRepository.save(entity), UserDTO.class);
    }



}
