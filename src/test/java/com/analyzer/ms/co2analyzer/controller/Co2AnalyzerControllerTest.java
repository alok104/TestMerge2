package com.analyzer.ms.co2analyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(Co2AnalyzerController.class)
public class Co2AnalyzerControllerTest {
 
  @Autowired
  private MockMvc mvc;

}
