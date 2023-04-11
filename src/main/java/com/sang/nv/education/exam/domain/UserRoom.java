package com.sang.nv.education.exam.domain;

import com.sang.commonclient.domain.UserDTO;
import com.sang.commonmodel.enums.UserType;
import com.sang.commonutil.IdUtils;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Setter(AccessLevel.PRIVATE)
@Getter
public class UserRoom {
    String id;
    String roomId;
    String userId;
    String username;
    UserType userType;
    String code;
    String fullName;
    UserDTO userDTO;
    Boolean deleted;

    public UserRoom(String userId, String roomId)
    {
        this.id = IdUtils.nextId();
        this.userId = userId;
        this.roomId = roomId;
        this.deleted = false;
    }

    public UserRoom(String roomId, UserDTO userDTO)
    {
        this.id = IdUtils.nextId();
        this.roomId = roomId;
        if (Objects.nonNull(userDTO))
        {
            this.userId = userDTO.getId();
            this.userType = userDTO.getUserType();
            this.username = userDTO.getUsername();
            this.fullName = userDTO.getFullName();
            this.code = userDTO.getCode();
        }
        this.deleted = false;
    }


    public void enrichUserDTO(UserDTO userDTO){
        this.userDTO = userDTO;
    }

    public void deleted(){
        this.deleted = true;
    }

    public void unDelete(){
        this.deleted = false;
    }
}
