package com.project.restaurant.unit.facade;

import com.project.restaurant.adapters.facadeimpl.MvcFacadeImpl;
import com.project.restaurant.domain.dto.OrderDto;
import com.project.restaurant.domain.dto.UserProfileDto;
import com.project.restaurant.domain.facade.MvcFacade;
import com.project.restaurant.domain.mapstruct.*;
import com.project.restaurant.domain.repository.UserRepository;
import com.project.restaurant.domain.service.OrderService;
import com.project.restaurant.domain.service.ProduceService;
import com.project.restaurant.domain.service.RoleService;
import com.project.restaurant.domain.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.project.restaurant.util.Utility.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MvcFacadeTest {
    private final ProduceMapper produceMapper = Mappers.getMapper(ProduceMapper.class);
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    private final UserRegisterMapper userRegisterMapper = Mappers.getMapper(UserRegisterMapper.class);
    private final OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);
    private final UserProfileMapper userProfileMapper = Mappers.getMapper(UserProfileMapper.class);

    private final ProduceService produceService = mock();

    private final UserService userService = mock();
    private final OrderService orderService = mock();
    private final RoleService roleService = mock();
    private final UserRepository userRepository = mock();
    private final PasswordEncoder passwordEncoder = mock();
    private final RabbitTemplate rabbitTemplate = mock();

    private final MvcFacade mvcFacade = new MvcFacadeImpl(produceMapper, userMapper, userRegisterMapper, orderMapper, userProfileMapper, produceService, userService, orderService, roleService, userRepository, passwordEncoder, rabbitTemplate);

    @Test
    void testGetAllProduces() {
        when(produceService.getAllProduces()).thenReturn(PRODUCE_LIST);

        assertThat(mvcFacade.getAllProduces()).isEqualTo(PRODUCE_DTO_LIST);

        verify(produceService).getAllProduces();

    }

    @Test
    void testMakeOrder() {
        String username = USER_LIST.get(0).getUsername();

        when(userService.getUserByUsername(username)).thenReturn(USER_LIST.get(0));
        when(produceService.getProduceById(1)).thenReturn(PRODUCE_LIST.get(0));
        when(produceService.getProduceById(2)).thenReturn(PRODUCE_LIST.get(1));

        OrderDto orderDto = mvcFacade.makeOrder(username, USER_ORDER_DTO);


        verify(userService).getUserByUsername(username);

        assertThat(orderDto).isEqualTo(ORDER_DTO_LIST.get(0));

    }

    @Test
    void testGetUserByUsername() {
        String username = "test";

        when(userService.getUserByUsername(username)).thenReturn(USER_LIST.get(0));

        assertThat(mvcFacade.getUserByUsername(username)).isEqualTo(USER_DTO_LIST.get(0));
    }

    @Test
    void testSaveUserWithPassword() {
        when(userService.save(any())).thenReturn(USER_LIST.get(0));
        UserProfileDto returned = mvcFacade.saveUser(USER_PROFILE_DTO);
        verify(userRepository).modifyUserPassword(any(), any());

        assertThat(returned).isEqualTo(USER_PROFILE_DTO);

    }
    @Test
    void testGetUserProfileByUsername(){
        when(userService.getUserByUsername(USER_PROFILE_DTO.getUsername())).thenReturn(USER_LIST.get(0));

        assertThat(USER_PROFILE_DTO).isEqualTo(mvcFacade.getUserProfileByUsername(USER_PROFILE_DTO.getUsername()));
    }
}
