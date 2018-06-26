package com.ps.backend.mapper;

import com.ps.backend.entity.User;
import com.ps.common.dto.NameIdDTO;
import com.ps.common.dto.UserDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Collection;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDTO userDTO);

    @Mapping(target = "password", ignore = true)
    UserDTO toDto(User user);

    @Mapping(target = "name", source = "username")
    NameIdDTO toNameIdDTO(User user);

    Set<NameIdDTO> toNameIdDTOs(Collection<User> users);
}
