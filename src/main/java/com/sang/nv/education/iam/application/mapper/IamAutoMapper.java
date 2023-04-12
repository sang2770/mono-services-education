package com.sang.nv.education.iam.application.mapper;


import com.sang.commonmodel.mapper.BaseAutoMapper;
import com.sang.nv.education.iam.application.dto.request.Classes.ClassesCreateOrUpdateRequest;
import com.sang.nv.education.iam.application.dto.request.DepartmentCreateOrUpdateRequest;
import com.sang.nv.education.iam.application.dto.request.KeyCreateOrUpdateRequest;
import com.sang.nv.education.iam.application.dto.request.RoleCreateOrUpdateRequest;
import com.sang.nv.education.iam.application.dto.request.User.UserCreateRequest;
import com.sang.nv.education.iam.application.dto.request.User.UserUpdateRequest;
import com.sang.nv.education.iam.domain.command.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IamAutoMapper extends BaseAutoMapper {
    DepartmentCreateOrUpdateCmd from(DepartmentCreateOrUpdateRequest request);
    ClassesCreateOrUpdateCmd from(ClassesCreateOrUpdateRequest request);
    KeyCreateOrUpdateCmd from(KeyCreateOrUpdateRequest request);
    RoleCreateOrUpdateCmd from(RoleCreateOrUpdateRequest request);
    UserCreateOrUpdateCmd from(UserUpdateRequest request);
    UserCreateOrUpdateCmd from(UserCreateRequest request);

}
