package cst438hw2.controller;

import cst438hw2.domain.CityInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import cst438hw2.service.CityService;

@Controller
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	@GetMapping("/cities/{city}")
	public String getWeather(@PathVariable("city") String cityName, Model model) {
		CityInfo searchedCity = cityService.getCityInfo(cityName);

		if (searchedCity == null) {
			return "city_not_found";
		}
		else {
			model.addAttribute("cityInfo", searchedCity);
			return "city_result";
		}
	}
}