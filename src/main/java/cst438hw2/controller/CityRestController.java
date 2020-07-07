package cst438hw2.controller;

import cst438hw2.service.WeatherService;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import cst438hw2.domain.*;
import cst438hw2.service.CityService;

@RestController
public class CityRestController {

  @Autowired
  private CityService cityService;

  @Autowired
  private CityRepository cityRepository;

  @Autowired
  private WeatherService weatherService;

  @GetMapping("/api/cities/{city}")
  public ResponseEntity<CityInfo> getWeather(@PathVariable("city") String cityName) {

    List<City> cities = cityRepository.findByName(cityName);
    if (cities.size() == 0) {
      return new ResponseEntity<CityInfo>(HttpStatus.NOT_FOUND); //city name not found send 404
    }
    else {
      City currentCity = cities.get(0); //in case of multiple cities, take the first one

      CityInfo restCity = cityService.getCityInfo(currentCity.getName());

      return new ResponseEntity<CityInfo>(restCity, HttpStatus.OK);
    }

  }

}