package com.kosickaakademia.onlineworkplaceserver.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosickaakademia.onlineworkplaceserver.dto.UserDTO;
import com.kosickaakademia.onlineworkplaceserver.entities.LabelEntity;
import com.kosickaakademia.onlineworkplaceserver.entities.WorkplaceEntity;
import com.kosickaakademia.onlineworkplaceserver.services.WorkplaceServiceImpl;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class WorkplaceControllerTest {
    @Mock
    WorkplaceServiceImpl workplaceService;

    @InjectMocks
    WorkplaceController workplaceController;

    MockMvc mockMvc;
    ObjectMapper objectMapper;

    List<WorkplaceEntity> workplaceEntityList;
    List<UserDTO> userDTOList;
    List<LabelEntity> labelEntityList;

    LabelEntity labelEntity;
    WorkplaceEntity workplaceEntity;

    @BeforeEach
    void setUp() {
        workplaceEntityList = new ArrayList<>();
        workplaceEntity = new WorkplaceEntity();
        workplaceEntity.setId(1L);
        workplaceEntity.setName("Test Workplace");
        workplaceEntity.setDescription("This is test description");
        workplaceEntityList.add(workplaceEntity);

        val user = new UserDTO(1L, "Adam", "Ivan", "adam.ivan@kosickaakademia.sk");
        userDTOList = new ArrayList<>();
        userDTOList.add(user);

        labelEntity = new LabelEntity();
        labelEntity.setId(1L);
        labelEntity.setName("Front End");
        labelEntity.setColor("#FFFFFF");
        labelEntityList = new ArrayList<>();
        labelEntityList.add(labelEntity);

        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders
                .standaloneSetup(workplaceController)
                .build();
    }

    @Test
    void getAllWorkplaces() throws Exception {
        when(workplaceService.getAllUserWorkplace(1L)).thenReturn(workplaceEntityList);

        mockMvc.perform(get("/workplaces?userId=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(workplaceEntityList.size())))
                .andExpect(jsonPath("$[0].name", is(workplaceEntityList.get(0).getName())))
                .andExpect(jsonPath("$[0].description", is(workplaceEntityList.get(0).getDescription())));

        val workplace = new WorkplaceEntity();
        workplaceEntityList.add(workplace);

        mockMvc.perform(get("/workplaces?userId=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(workplaceEntityList.size())));
    }

    @Test
    void addWorkplace() throws Exception {
        when(workplaceService.addWorkplace(ArgumentMatchers.any(WorkplaceEntity.class))).thenReturn(workplaceEntity);

        mockMvc.perform(
                post("/workplace")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(workplaceEntity)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(workplaceEntity.getName())))
                .andExpect(jsonPath("$.description", is(workplaceEntity.getDescription())));
    }

    @Test
    void deleteUserFromWorkplace() {

    }

    @Test
    void addUserToWorkplace() {
    }

    @Test
    void getAllWorkplaceUsers() throws Exception {
        when(workplaceService.getAllUsers(1L)).thenReturn(userDTOList);

        mockMvc.perform(get("/workplace/1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(userDTOList.size())))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].userName", is(userDTOList.get(0).getUserName())))
                .andExpect(jsonPath("$[0].userSurname", is(userDTOList.get(0).getUserSurname())))
                .andExpect(jsonPath("$[0].email", is(userDTOList.get(0).getEmail())));

    }

    @Test
    void getAllWorkplaceLabels() throws Exception {
        when(workplaceService.getAllLabels(1L)).thenReturn(labelEntityList);

        mockMvc.perform(get("/workplace/1/labels"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(labelEntityList.size())))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is(labelEntityList.get(0).getName())))
                .andExpect(jsonPath("$[0].color", is(labelEntityList.get(0).getColor())));
    }

    @Test
    void addLabelToWorkplace() throws Exception {
        when(workplaceService.addLabel(ArgumentMatchers.any(LabelEntity.class), eq(1L))).thenReturn(labelEntity);

        mockMvc.perform(
                post("/workplace/1/label")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(labelEntity)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(labelEntity.getName())))
                .andExpect(jsonPath("$.color", is(labelEntity.getColor())));
    }

    @Test
    void deleteLabelFromWorkplace() {
    }
}
