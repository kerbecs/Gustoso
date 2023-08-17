package com.project.restaurant.integration.glue;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.restaurant.RestaurantApplication;
import com.project.restaurant.domain.dto.UserDescriptionDto;
import com.project.restaurant.domain.dto.UserRegisterDto;
import com.project.restaurant.domain.entity.Role;
import com.project.restaurant.domain.roles.Roles;
import io.cucumber.java.DataTableType;
import io.cucumber.java.DefaultDataTableCellTransformer;
import io.cucumber.java.DefaultDataTableEntryTransformer;
import io.cucumber.java.DefaultParameterTransformer;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = RestaurantApplication.class)
@ActiveProfiles("test")
public class CucumberConfiguration {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @DefaultParameterTransformer
    @DefaultDataTableEntryTransformer
    @DefaultDataTableCellTransformer
    public Object transformer(Object fromValue, Type toValueType) {
        return objectMapper.convertValue(fromValue, objectMapper.constructType(toValueType));
    }

    @DataTableType
    public UserRegisterDto getUserRegisterDto(Map<String, String> map) {
        return UserRegisterDto.builder()
                .username(map.get("username"))
                .password(map.get("password"))
                .repeatPassword(map.get("password"))
                .isActive(Integer.getInteger(map.get("isActive")))
                .userDescriptionDto(UserDescriptionDto.builder()
                        .firstName(map.get("firstName"))
                        .lastName(map.get("lastName"))
                        .email(map.get("email"))
                        .city(map.get("city"))
                        .address(map.get("address"))
                        .build())
                .build();
    }

    private List<Role> getRoles(String role) {
        Roles theRole = Roles.valueOf(role);

        return Stream.of(Roles.values())
                .filter(r ->
                        r.ordinal() <= theRole.ordinal())
                .map(r -> Role.builder()
                        .role(r)
                        .build())
                .toList();

    }


}
