package com.project.restaurant.adapters.facadeimpl;

import com.project.restaurant.domain.dto.*;
import com.project.restaurant.domain.entity.Order;
import com.project.restaurant.domain.entity.OrderProduce;
import com.project.restaurant.domain.entity.Produce;
import com.project.restaurant.domain.entity.User;
import com.project.restaurant.domain.facade.MvcFacade;
import com.project.restaurant.domain.mapstruct.*;
import com.project.restaurant.domain.repository.UserRepository;
import com.project.restaurant.domain.roles.Roles;
import com.project.restaurant.domain.service.OrderService;
import com.project.restaurant.domain.service.ProduceService;
import com.project.restaurant.domain.service.RoleService;
import com.project.restaurant.domain.service.UserService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MvcFacadeImpl implements MvcFacade {
    private final ProduceMapper produceMapper;
    private final UserMapper userMapper;
    private final UserRegisterMapper userRegisterMapper;
    private final OrderMapper orderMapper;
    private final UserProfileMapper userProfileMapper;

    private final ProduceService produceService;

    private final UserService userService;
    private final OrderService orderService;
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EntityManager entityManager;


    @Override
    public List<ProduceDto> getAllProduces() {
        List<Produce> list = produceService.getAllProduces();
        return list.stream()
                .map(produceMapper::produceToProduceDto)
                .toList();
    }

    @Override
    @Transactional
    public OrderDto makeOrder(String username, UserOrderDto userOrderDto) {
        User user = userService.getUserByUsername(username);
        Order order ;

        try{
            order = createOrder(user,userOrderDto);

            orderService.save(order);

        }
        catch (Exception e){
            return null;
        }

        return orderMapper.orderToOrderDto(order);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userService.getUserByUsername(username);

        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto saveUser(UserRegisterDto userRegisterDto) {
        User user = userRegisterMapper.userRegisterDtoToUser(userRegisterDto);

        setDefaultRoleForNewUser(user);
        encryptPassword(user);

        return userMapper.userToUserDto(userService.save(user));
    }

    @Override
    @Transactional
    public UserProfileDto saveUser(UserProfileDto userProfileDto) {
        User user = userProfileMapper.userProfileDtoToUser(userProfileDto);

        user = userService.save(user);

        if(userProfileDto.getPassword() != null && !userProfileDto.getPassword().isEmpty()){
            encryptPassword(user);
            userRepository.modifyUserPassword(user.getPassword(),user.getUsername());
        }

        return userProfileMapper.userToUserProfileDto(user);
    }

    @Override
    public UserProfileDto getUserProfileByUsername(String username) {
        User user = userService.getUserByUsername(username);

        return userProfileMapper.userToUserProfileDto(user);
    }

    @Override
    public void checkPasswords(String password1, String password2, BindingResult bindingResult) {
        if(!password1.equals(password2)){
            FieldError objectError1 = new FieldError("userRegister","password","Password should be the same");
            bindingResult.addError(objectError1);
        }
    }

    private List<Produce> getProduceList(Integer[] producesId) {
        List<Produce> produceList = new ArrayList<>();

        List.of(producesId)
                .forEach(produce -> produceList.add(produceService.getProduceById(produce)));

        return produceList;

    }

    private Order createOrder(User user,UserOrderDto userOrderDto) {
        Order order = new Order();
        List<Produce> produceList = getProduceList(userOrderDto.getOrderedProduceList());
        double cost = countOrderCost(produceList);
        List<OrderProduce> orderProduceList = createOrderProduceList(produceList,order);


        order.setCost(cost);
        order.setDate(LocalDateTime.now());
        order.setUser(user);
        order.setOrderProduceList(orderProduceList);

        return order;
    }

    private List<OrderProduce> createOrderProduceList(List<Produce> produceList, Order order) {
        List<OrderProduce> orderProduceList = new ArrayList<>();

        produceList.forEach(p -> orderProduceList.add(new OrderProduce(order, p, p.getPrice())));
        order.setOrderProduceList(orderProduceList);

        return orderProduceList;
    }

    private double countOrderCost(List<Produce> produceList) {
        return produceList.stream()
                .mapToDouble(Produce::getPrice)
                .sum();
    }
    private void setDefaultRoleForNewUser(User user){
        user.setRoleList(List.of(roleService.getRole(Roles.MEMBER)));
    }
    private void encryptPassword(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
