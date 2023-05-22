package kr.npost.cms.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.npost.cms.services.KafkaProducer;

@RestController
public class TestController {

  @Autowired
  private KafkaProducer kafkaProducer;

  @GetMapping(value = "/alive")
  public String alive() throws Exception {

    
    return "is alive : ";
  }

}
