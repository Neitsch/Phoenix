/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.server.rest;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoenix.server.data.TestCaseRepository;
import com.phoenix.to.TestCase;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Jan 28, 2016
 */
@RunWith(MockitoJUnitRunner.class)
public class TestCaseRestController_getAllTestCases_Test {
  private ObjectMapper mapper;
  private MockMvc mockMvc;
  @InjectMocks
  TestCaseRestController controller;
  @Mock
  TestCaseRepository repository;

  /**
   * @author nschuste
   * @version 1.0.0
   * @throws java.lang.Exception
   * @since Jan 28, 2016
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {}

  /**
   * @author nschuste
   * @version 1.0.0
   * @throws java.lang.Exception
   * @since Jan 28, 2016
   */
  @AfterClass
  public static void tearDownAfterClass() throws Exception {}

  /**
   * @author nschuste
   * @version 1.0.0
   * @throws java.lang.Exception
   * @since Jan 28, 2016
   */
  @Before
  public void setUp() throws Exception {
    this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller).build();
    this.mapper = new ObjectMapper();
  }

  /**
   * @author nschuste
   * @version 1.0.0
   * @throws java.lang.Exception
   * @since Jan 28, 2016
   */
  @After
  public void tearDown() throws Exception {}

  @Test
  public final void test() throws Exception {
    final List<TestCase> res = new ArrayList<>();
    res.add(new TestCase());
    Mockito.when(this.repository.findAll()).thenReturn(res);
    final MvcResult result =
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/tc"))
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(res)))
            .andReturn();
    final List<TestCase> res2 =
        this.mapper.readValue(result.getResponse().getContentAsString(), this.mapper
            .getTypeFactory().constructCollectionType(List.class, TestCase.class));
    Assert.assertThat(res, CoreMatchers.is(res2));
    Mockito.verify(this.repository, Mockito.only()).findAll();
  }
}
