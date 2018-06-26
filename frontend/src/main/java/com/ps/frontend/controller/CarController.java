package com.ps.frontend.controller;

import com.ps.common.dto.CarDTO;
import com.ps.common.dto.NameIdDTO;
import com.ps.common.dto.UserDTO;
import com.ps.frontend.controller.command.CarCommand;
import com.ps.frontend.gateway.CarGateway;
import com.ps.frontend.gateway.UserGateway;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/car")
public class CarController {

    private final CarGateway carGateway;
    private final UserGateway userGateway;

    @Autowired
    public CarController(CarGateway carGateway,
                         UserGateway userGateway) {
        this.carGateway = carGateway;
        this.userGateway = userGateway;
    }


    @GetMapping("/{id}")
    public ModelAndView details(@PathVariable("id") Long id, ModelAndView mav) {
        CarDTO car = carGateway.findById(id);

        mav.addObject("car", car);
        mav.setViewName("car/details");
        return mav;
    }

    @GetMapping("/list")
    public ModelAndView list(ModelAndView mav) {
        List<CarDTO> all = carGateway.findAll();

        mav.addObject("cars", all);
        mav.setViewName("car/list");
        return mav;
    }

    @GetMapping("/create")
    public ModelAndView getCreate(ModelAndView mav, @ModelAttribute("car") CarCommand car) {
        List<UserDTO> users = userGateway.findAll();

        mav.addObject("users", users);
        mav.setViewName("car/create");
        return mav;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView getEdit(@PathVariable("id") Long id, ModelAndView mav) {
        List<UserDTO> users = userGateway.findAll();
        CarDTO carDTO = carGateway.findById(id);
        CarCommand carCommand = carDTOToCarCommand(carDTO);

        mav.addObject("users", users);
        mav.addObject("car", carCommand);
        mav.setViewName("car/create");
        return mav;
    }

    @PostMapping("/save")
    public String save(ModelAndView mav, @Valid CarCommand carCommand, BindingResult result) {
        Long savedId = carGateway.save(carCommandToCarDTO(carCommand));
        return "redirect:/car/" + savedId;
    }

    private CarDTO carCommandToCarDTO(CarCommand carCommand) {
        CarDTO carDTO = new CarDTO();
        carDTO.setName(carCommand.getName());

        NameIdDTO owner = new NameIdDTO();
        owner.setId(carCommand.getOwner());
        carDTO.setOwner(owner);

        List<NameIdDTO> drivers = carCommand.getDrivers().stream().map(id -> {
            NameIdDTO nameIdDTO = new NameIdDTO();
            nameIdDTO.setId(id);
            return nameIdDTO;
        }).collect(Collectors.toList());
        carDTO.setDrivers(drivers);

        return carDTO;
    }

    private CarCommand carDTOToCarCommand(CarDTO carDTO) {
        CarCommand carCommand = new CarCommand();
        carCommand.setId(carDTO.getId());
        carCommand.setName(carDTO.getName());
        carCommand.setOwner(carDTO.getOwner().getId());
        carCommand.setDrivers(carDTO.getDrivers().stream().map(NameIdDTO::getId).collect(Collectors.toSet()));

        return carCommand;
    }
}
