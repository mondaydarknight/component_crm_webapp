package com.smartbee.crm.core.mappers;

import com.smargbee.openapi_generated.crm.model.ApiUser;
import com.smartbee.crm.core.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    default String map(Enum<?> e) {
        return e.toString();
    }

    default Enum<?> map(String s) {
        return ApiUser.AuthorityEnum.fromValue(s);
    }

    ApiUser toApiUser(User user);

    User toUser(ApiUser user);
}
