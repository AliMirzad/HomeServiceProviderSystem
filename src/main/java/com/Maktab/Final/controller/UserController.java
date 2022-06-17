package com.Maktab.Final.controller;

import com.Maktab.Final.controller.dto.GridSearchDTO;
import com.Maktab.Final.controller.dto.UserDTO;
import com.Maktab.Final.model.entity.Customer;
import com.Maktab.Final.model.entity.Expert;
import com.Maktab.Final.model.entity.baseEntity.User;
import com.Maktab.Final.model.exception.LogicErrorException;
import com.Maktab.Final.model.service.CustomerService;
import com.Maktab.Final.model.service.ExpertService;
import com.Maktab.Final.model.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class UserController {
	private final UserService userService;
	private final ExpertService expertService;
	private final CustomerService customerService;
	private final ModelMapper modelMapper = new ModelMapper();

	public UserController(UserService userService, ExpertService expertService, CustomerService customerService) {
		this.userService = userService;
		this.expertService = expertService;
		this.customerService = customerService;
	}

	@PostMapping("/user/register")
	public String register(@ModelAttribute @RequestBody UserDTO userDTO) throws IOException {
		if (userDTO.getType().equals("expert")) {
			Expert expert = Expert.builder().firstName(userDTO.getFirstName()).lastName(userDTO.getLastName()).email(userDTO.getEmail()).nationalCode(userDTO.getNationalCode()).password(userDTO.getPassword()).build();
			expert.setProfileImage(userDTO.getProfileImage().getBytes());
			System.out.println(expert);
			expertService.create(expert);
		} else if (userDTO.getType().equals("customer")) {
			Customer customer = Customer.builder().firstName(userDTO.getFirstName()).lastName(userDTO.getLastName()).email(userDTO.getEmail()).nationalCode(userDTO.getNationalCode()).password(userDTO.getPassword()).build();
			customer.setProfileImage(userDTO.getProfileImage().getBytes());
			customerService.create(customer);
		}
		return "ok";
	}

	@GetMapping("/user/grid-search/")
	public List<UserDTO> gridSearch(@RequestParam(required = false, name = "id") Integer id,
									@RequestParam(required = false, name = "email") String email,
									@RequestParam(required = false, name = "firstName") String firstName,
									@RequestParam(required = false, name = "lastName") String lastName) {
		List<User> userList = userService.gridSearch(id, email, firstName, lastName);
		List<UserDTO> userDTOList = new ArrayList<>();
		for (User u : userList) {
			UserDTO userDTO = modelMapper.map(u, UserDTO.class);
			userDTOList.add(userDTO);
		}
		return userDTOList;
	}

	@PostMapping("/user/gridSearch/")
	public ResponseEntity<List<UserDTO>> gridSearchT(@ModelAttribute @RequestBody(required = false) GridSearchDTO gridSearchDTO) {
		System.out.println(gridSearchDTO);
//		return null;
//		System.out.println(gridSearchDTO.toString());
		List<User> userList = userService.gridSearch(gridSearchDTO.getId(), gridSearchDTO.getEmail(), gridSearchDTO.getFirstName(), gridSearchDTO.getFirstName());
		List<UserDTO> userDTOList = new ArrayList<>();
		for (User u : userList) {
			UserDTO userDTO = UserDTO.builder().id(u.getId()).email(u.getEmail()).firstName(u.getFirstName()).lastName(u.getLastName()).build();
			userDTOList.add(userDTO);
		}
		userDTOList.forEach(System.out::println);
		return ResponseEntity.ok(userDTOList);
	}
}
