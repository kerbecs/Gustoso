package com.project.restaurant.integration.glue;

import com.project.restaurant.domain.dto.UserDto;
import com.project.restaurant.domain.dto.UserRegisterDto;
import com.project.restaurant.domain.facade.MvcFacade;
import com.project.restaurant.domain.repository.UserDescriptionRepository;
import com.project.restaurant.domain.repository.UserRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserControllerTest {
    private List<UserDto> registeredUsers = new ArrayList<>();
    private List<UserDto> retrievedUsers;
    private String username;

    @Autowired
    private MvcFacade mvcFacade;
    @Autowired
    private TestRestTemplate template;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDescriptionRepository userDescriptionRepository;

    @Given("^The next users exist$")
    @Transactional
    public void createUsers(List<UserRegisterDto> list) {
        list.forEach(user -> registeredUsers.add(mvcFacade.saveUser(user)));

        registeredUsers.forEach(user -> {
            user.setIsActive(0);
            user.getUserDescriptionDto().setOrders(0);
        });
    }

    @When("^All users are retrieved from the database$")
    public void retrieveUsers() {
        retrievedUsers = List.of(template.getForObject("/api/v1/users", UserDto[].class));
    }

    @When("User with username {string} is retrieved")
    public void setUsernameToRetrieve(String username) {
        this.username = username;
    }

    @Then("^All users from above should be returned$")
    public void compareUsers() {
        assertThat(retrievedUsers).isEqualTo(registeredUsers);
    }

    @Then("^Delete all inserted data$")
    public void deleteUsers() {
        userRepository.deleteAll();
        userDescriptionRepository.deleteAll();
    }

    @Then("^This user should be returned$")
    public void compareUser() {
        UserDto user = template.getForObject("/api/v1/users/" + username, UserDto.class);

        assertThat(user).isEqualTo(retrievedUsers.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .get());
    }


}
