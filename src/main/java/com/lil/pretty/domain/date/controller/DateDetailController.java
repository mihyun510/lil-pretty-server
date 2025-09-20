package com.lil.pretty.domain.date.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lil.pretty.domain.date.service.DateDetailService;
import com.lil.pretty.domain.diet.controller.DietDetailController;
import com.lil.pretty.domain.diet.service.DietDetailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j	
@RestController
@RequestMapping("api/date/detail")
@RequiredArgsConstructor
public class DateDetailController {
	private final DateDetailService dateDetailService; 
	
}
