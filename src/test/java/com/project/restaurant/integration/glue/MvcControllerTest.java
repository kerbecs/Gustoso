package com.project.restaurant.integration.glue;

import com.project.restaurant.adapters.exception.NoSuchElementException;
import com.project.restaurant.domain.dto.UserDto;
import com.project.restaurant.domain.dto.UserRegisterDto;
import com.project.restaurant.domain.facade.UserFacade;
import com.project.restaurant.domain.repository.UserDescriptionRepository;
import com.project.restaurant.domain.repository.UserRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MvcControllerTest {
    private String page;
    private String endpoint;
    private Integer status;
    private UserRegisterDto userToRegister;
    private UserDto registeredUser;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserFacade userFacade;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDescriptionRepository userDescriptionRepository;

    @Given("^An user registers with the next credentials$")
    public void getUserToRegister(UserRegisterDto userRegisterDto) {
        this.userToRegister = userRegisterDto;
    }

    @When("^User uses get (.*) endpoint with no parameter$")
    public void setEndpoint(String endpoint) throws Exception {
        this.endpoint = endpoint;

    }

    @Then("^It should receive (.*) status$")
    public void setStatus(int status) {
        this.status = status;
    }

    @Then("^It should receive (.*) page$")
    public void verifyPage(String page) throws Exception {
        this.page = mockMvc.perform(get(endpoint))
                .andExpect(status().is(status))
                .andReturn()
                .getModelAndView()
                .getViewName();

        assertThat(this.page).isEqualTo(page);
    }

    @Then("^The user should be registered by receiving (.*) page$")
    public void registerUser(String page) throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/register")
                        .flashAttr("userRegister", userToRegister))
                .andExpect(status().is(status))
                .andReturn();

        ModelAndViewAssert.assertViewName(mvcResult.getModelAndView(), page);

        try {
            registeredUser = userFacade.getUserByUsername(userToRegister.getUsername());

        } catch (Exception e) {
        }
    }

    @Then("^It should have (.*) role by default$")
    public void testRole(String role) {
        assertThat(registeredUser.getRole()).isEqualTo(role);
    }

    @Then("^It should have inactive status$")
    public void testStatus() {
        assertThat(registeredUser.getIsActive()).isEqualTo(0);
    }

    @Then("Inserted user should be deleted")
    public void deleteUser() {
        userRepository.deleteAll();
        userDescriptionRepository.deleteAll();
    }

    @Then("^The user should not be registered with success$")
    public void testIfUserExists() {
        assertThatThrownBy(() -> userFacade.getUserByUsername(userToRegister.getUsername()))
                .isInstanceOf(NoSuchElementException.class);
    }
}
