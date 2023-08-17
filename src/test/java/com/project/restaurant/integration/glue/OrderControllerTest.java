package com.project.restaurant.integration.glue;

import com.project.restaurant.domain.dto.*;
import com.project.restaurant.domain.facade.MvcFacade;
import com.project.restaurant.domain.facade.UserFacade;
import com.project.restaurant.domain.repository.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class OrderControllerTest {
    private UserDto userDto;
    private List<ProduceDto> produceDtoList = new ArrayList<>();
    private final List<OrderProduceDto> orderProduceDtoList = new ArrayList<>();
    private List<OrderDto> orderDtoList = new ArrayList<>();
    List<OrderDto> returnedUserOrder = new ArrayList<>();
    private OrderDto returnedOrderDto;
    private Integer userId;


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestRestTemplate template;
    @Autowired
    private UserFacade userFacade;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProduceRepository produceRepository;
    @Autowired
    private UserDescriptionRepository userDescriptionRepository;
    @Autowired
    private MvcFacade mvcFacade;



    @Given("^The next user exists$")
    public void setUser(UserRegisterDto userRegisterDto) throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/register")
                        .flashAttr("userRegister", userRegisterDto)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        assertViewName(mvcResult.getModelAndView(), "activated");

        userDto = userFacade.getUserByUsername(userRegisterDto.getUsername());

        userRepository.activateUser(userDto.getUsername());
    }

    @Given("The next produces exist")
    public void saveProduces(List<ProduceDto> list) {
        produceDtoList = list;

        produceDtoList.forEach(produce -> {
                    HttpHeaders httpHeaders = new HttpHeaders();
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);

                    HttpEntity<ProduceDto> httpEntity = new HttpEntity<>(produce, httpHeaders);

                    template.postForObject("/api/v1/produces", httpEntity, String.class);
                }

        );
        produceDtoList = List.of(template.getForObject("/api/v1/produces", ProduceDto[].class));
    }

    @Given("^The user makes an order with both produces$")
    @WithMockUser(username = "eduard.mititiuc1", roles = {"MEMBER"})
    public void makeFirstOrder() {
        OrderDto orderDto = OrderDto.builder()
                .cost(produceDtoList.stream()
                        .mapToDouble(ProduceDto::getPrice)
                        .sum())
                .date(LocalDateTime.now())
                .user(userDto)
                .build();

        produceDtoList.forEach(produce ->
                orderProduceDtoList.add(
                        OrderProduceDto.builder()
                                .cost(produce.getPrice())
                                .produce(produce)
                                .build()
                )
        );
        orderDto.setOrderProduceList(orderProduceDtoList);

        UserOrderDto userOrderDto = UserOrderDto.builder()
                .userId(userDto.getId())
                .orderedProduceList(new Integer[]{produceDtoList.get(0).getId(), produceDtoList.get(1).getId()})
                .build();

        mvcFacade.makeOrder(userDto.getUsername(), userOrderDto);

    }

    @When("^All orders are retrieved from the database$")
    public void getAllOrders() {
        orderDtoList = List.of(template.getForObject("/api/v1/orders", OrderDto[].class));
    }

    @When("^The first order's id is used$")
    public void getOrder() {
        returnedOrderDto = template.getForObject("/api/v1/orders/" + orderDtoList.get(0).getId(), OrderDto.class);
    }

    @When("User's id is used")
    public void getUserUsername() {
        this.userId = userDto.getId();
    }

    @Then("^Its orders should be returned$")
    public void getUserOrders() {
        returnedUserOrder = List.of(template.getForObject("/api/v1/orders/users/" + userId, OrderDto[].class));
    }

    @Then("^There should be 1 order$")
    public void checkNumberOfOrders() {
        assertThat(orderDtoList).hasSize(1);
    }

    @Then("^The order should have 2 produces$")
    public void checkFirstOrder() {
        assertThat(orderDtoList.get(0).getOrderProduceList()).hasSize(2);
    }

    @Then("^It should return the first order$")
    public void checkOrder() {
        assertThat(returnedOrderDto).isEqualTo(orderDtoList.get(0));
    }

    @Then("^These orders should be the one defined above$")
    public void checkOrders() {
        assertThat(returnedUserOrder.get(0)).isEqualTo(orderDtoList.get(0));
    }

    @Then("^All data should be deleted from the data base$")
    public void deleteAllData() {
        orderRepository.deleteAll();
        userDescriptionRepository.deleteAll();
        userRepository.deleteAll();
        produceRepository.deleteAll();
    }

}
