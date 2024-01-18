package com.example.test_module_4.controller;

import com.example.test_module_4.model.City;
import com.example.test_module_4.model.Country;
import com.example.test_module_4.service.ICityService;
import com.example.test_module_4.service.ICountryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private ICityService iCityService;

    @Autowired
    private ICountryService iCountryService;

    @GetMapping()
    public String showList(Model model) {
        List<City> cities = (List<City>) iCityService.findAll();
        model.addAttribute("cities", cities);
        return "city/list";
    }

    @GetMapping("/add")
    public String showFormAdd(Model model) {
        model.addAttribute("countries", iCountryService.findAll());
        model.addAttribute("city", new City());
        return "city/edit";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("city") City city,
                      BindingResult bindingResult,
                      Model model) {
        if (!bindingResult.hasErrors()) {
            iCityService.save(city);
            return "redirect:/cities";
        }
        Iterable<Country> countries = iCountryService.findAll();
        model.addAttribute("countries", countries);
        model.addAttribute("city", city);
        return "city/edit";
    }

    @GetMapping("/views/{id}")
    public String showDetails(@PathVariable("id") String id,
                                 Model model) {
        Optional<City> cityOptional = iCityService.findById(Long.valueOf(id));
        if (cityOptional.isPresent()) {
            model.addAttribute("city", cityOptional.get());
            return "city/view";
        }
        return "redirect:/cities";
    }

    @GetMapping("/update/{id}")
    public String showFormUpdate(@PathVariable("id") String id,
                                 Model model) {
        Optional<City> cityOptional = iCityService.findById(Long.valueOf(id));
        if (cityOptional.isPresent()) {
            Iterable<Country> countries = iCountryService.findAll();
            model.addAttribute("countries", countries);
            model.addAttribute("city", cityOptional.get());
            return "city/edit";
        }
        return "redirect:/cities";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid @ModelAttribute City city,
                            BindingResult bindingResult,
                            Model model) {
            if (!bindingResult.hasErrors()) {
            iCityService.save(city);
            return "redirect:/cities";
        }
        Iterable<Country> countries = iCountryService.findAll();
        model.addAttribute("countries", countries);
        model.addAttribute("city", city);
        return "city/edit";
    }

    @GetMapping("/search")
    public String searchByName(@RequestParam("keyword") String searchName,
                               Model model) {
        Iterable<City> cities = iCityService.findByName(searchName);
        model.addAttribute("cities", cities);
        Iterable<Country> countries = iCountryService.findAll();
        model.addAttribute("countries", countries);
        return "city/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        iCityService.remove(Long.valueOf(id));
        return "redirect:/cities";
    }
}
