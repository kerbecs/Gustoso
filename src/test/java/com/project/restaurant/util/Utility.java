package com.project.restaurant.util;

import com.project.restaurant.domain.dto.*;
import com.project.restaurant.domain.entity.*;
import com.project.restaurant.domain.mapstruct.OrderMapper;
import com.project.restaurant.domain.mapstruct.OrderProduceMapper;
import com.project.restaurant.domain.mapstruct.ProduceMapper;
import com.project.restaurant.domain.mapstruct.UserMapper;
import com.project.restaurant.domain.roles.Roles;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.List;

public class Utility {
    public static final UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);
    public static final OrderMapper ORDER_MAPPER = Mappers.getMapper(OrderMapper.class);
    public static final ProduceMapper PRODUCE_MAPPER = Mappers.getMapper(ProduceMapper .class);
    public static final OrderProduceMapper ORDER_PRODUCE_MAPPER = Mappers.getMapper(OrderProduceMapper.class);

    public final static List<Role> ROLE_LIST = List.of(
            Role.builder()
                    .role(Roles.MEMBER)
                    .id(1)
                    .build(),
            Role.builder()
                    .role(Roles.ADMIN)
                    .id(2)
                    .build()
    );
    public final static List<User> USER_LIST = List.of(
            User.builder()
                    .id(1)
                    .username("testUsername1")
                    .password("test123")
                    .isActive(1)
                    .roleList(ROLE_LIST)
                    .userDescription(UserDescription.builder()
                            .firstName("FirstName1")
                            .lastName("LastName1")
                            .email("test1@gmail.com")
                            .job("job1")
                            .description("A test description")
                            .city("test city1")
                            .orders(1)
                            .address("Test address1")
                            .id(1)
                            .build()
                    )
                    .build(),
            User.builder()
                    .id(2)
                    .username("testUsername2")
                    .password("test123")
                    .isActive(1)
                    .roleList(List.of(ROLE_LIST.get(0)))
                    .userDescription(UserDescription.builder()
                            .firstName("FirstName2")
                            .lastName("LastName2")
                            .email("test2@gmail.com")
                            .job("job2")
                            .description("A test description")
                            .city("test city2")
                            .orders(1)
                            .address("Test address2")
                            .id(2)
                            .build()
                    )
                    .build()
    );
    public static final List<Produce> PRODUCE_LIST = List.of(
            Produce.builder()
                    .id(1)
                    .image("img1.png")
                    .name("Food1")
                    .price(10.0)
                    .ingredients("Ingredients 1")
                    .weight("500")
                    .build(),
            Produce.builder()
                    .id(2)
                    .image("img2.png")
                    .name("Food2")
                    .price(15.0)
                    .ingredients("Ingredients 2")
                    .weight("400")
                    .build()
    );
    public static final List<Order> ORDER_LIST = List.of(
            Order.builder()
                    .user(USER_LIST.get(0))
                    .date(LocalDateTime.now())
                    .cost(25.0)
                    .id(1)
                    .build(),
            Order.builder()
                    .user(USER_LIST.get(1))
                    .date(LocalDateTime.now().minusDays(5))
                    .cost(10.0)
                    .build()
    );
    public static final List<OrderProduce> ORDER_PRODUCE_LIST = List.of(
            OrderProduce.builder()
                    .order(ORDER_LIST.get(0))
                    .produce(PRODUCE_LIST.get(0))
                    .cost(PRODUCE_LIST.get(0).getPrice())
                    .build(),
            OrderProduce.builder()
                    .order(ORDER_LIST.get(0))
                    .produce(PRODUCE_LIST.get(1))
                    .cost(PRODUCE_LIST.get(1).getPrice())
                    .build(),
            OrderProduce.builder()
                    .order(ORDER_LIST.get(1))
                    .produce(PRODUCE_LIST.get(0))
                    .cost(PRODUCE_LIST.get(0).getPrice())
                    .build()
    );
    public static final List<OrderDto> ORDER_DTO_LIST = ORDER_LIST.stream()
            .map(ORDER_MAPPER::orderToOrderDto)
            .toList();
    public static final List<OrderProduceDto> ORDER_PRODUCE_DTO_LIST = ORDER_PRODUCE_LIST.stream()
            .map(ORDER_PRODUCE_MAPPER::orderProduceToOrderProduceDto)
            .toList();
    public static final List<ProduceDto> PRODUCE_DTO_LIST = PRODUCE_LIST.stream()
            .map(PRODUCE_MAPPER::produceToProduceDto)
            .toList();
    public static final List<UserDto> USER_DTO_LIST = USER_LIST.stream()
            .map(USER_MAPPER::userToUserDto)
            .toList();
    public static final UserOrderDto USER_ORDER_DTO = UserOrderDto.builder()
            .userId(USER_LIST.get(0).getId())
            .orderedProduceList(new Integer[]{1,2})
            .build();
    public static final UserProfileDto USER_PROFILE_DTO = UserProfileDto.builder()
            .id(USER_DTO_LIST.get(0).getId())
            .password(USER_DTO_LIST.get(0).getPassword())
            .isActive(USER_DTO_LIST.get(0).getIsActive())
            .username(USER_DTO_LIST.get(0).getUsername())
            .userDescription(USER_DTO_LIST.get(0).getUserDescriptionDto().getUserDescription())
            .city(USER_DTO_LIST.get(0).getUserDescriptionDto().getCity())
            .job(USER_DTO_LIST.get(0).getUserDescriptionDto().getJob())
            .email(USER_DTO_LIST.get(0).getUserDescriptionDto().getEmail())
            .address(USER_DTO_LIST.get(0).getUserDescriptionDto().getAddress())
            .orders(USER_DTO_LIST.get(0).getUserDescriptionDto().getOrders())
            .firstName(USER_DTO_LIST.get(0).getUserDescriptionDto().getFirstName())
            .lastName(USER_DTO_LIST.get(0).getUserDescriptionDto().getLastName())
            .descriptionId(USER_DTO_LIST.get(0).getUserDescriptionDto().getId())
            .roleList(ROLE_LIST)
            .build();

    static {
        ORDER_LIST.get(0).setOrderProduceList(List.of(ORDER_PRODUCE_LIST.get(0), ORDER_PRODUCE_LIST.get(1)));
        ORDER_LIST.get(1).setOrderProduceList(List.of(ORDER_PRODUCE_LIST.get(2)));

        ORDER_DTO_LIST.get(0).setOrderProduceList(List.of(ORDER_PRODUCE_DTO_LIST.get(0), ORDER_PRODUCE_DTO_LIST.get(1)));
        ORDER_DTO_LIST.get(1).setOrderProduceList(List.of(ORDER_PRODUCE_DTO_LIST.get(2)));
    }


}
