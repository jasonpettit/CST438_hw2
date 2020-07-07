package cst438hw2.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cst438hw2.domain.*;

@Service
public class CityService {
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private WeatherService weatherService;
	
	public CityInfo getCityInfo(String cityName) {
		List<City> city = cityRepository.findByName(cityName);

		if (city.size() == 0){
			return null;
		}

		City tempCity = city.get(0);
		Country country = countryRepository.findByCode(tempCity.getCountryCode());
		TempAndTime weather = weatherService.getTempAndTime(cityName);
		String time = Long.toString(weather.getTime());

		return new CityInfo(tempCity, country.getName(), weather.getTemp(), time);
	}
	
}
