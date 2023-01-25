package exercise.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.CityNotFoundException;
import exercise.model.City;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController()
public class CityController {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherService weatherService;

    // BEGIN
    @GetMapping(path = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<City> getCities() {
        return cityRepository.findAll();
    }

    @GetMapping(path = "/cities/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCity(@PathVariable Long id) {
        City city = cityRepository.findById(id).orElseThrow(
                () -> new CityNotFoundException("City not found"));
        return weatherService.getWeatherByCityName(city.getName());
    }

    @GetMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, String>> searchCity(@RequestParam(required = false) String name) {

        List<City> cities;
        if (Objects.isNull(name)) {
            cities = cityRepository.findAll();
        } else {
            cities = cityRepository.findByNameStartingWithIgnoreCase(name);
        }

        return cities.stream()
                .sorted(Comparator.comparing(c -> c.getName()))
                .map(c -> {
                    String json = weatherService.getWeatherByCityName(c.getName());
                    Map<String, String> cityWeather;
                    try {
                        cityWeather = mapper.readValue(json, new TypeReference<Map<String, String>>(){});
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    return  Map.of(
                    "temperature", cityWeather.get("temperature"),
                    "name", c.getName());
                }).collect(Collectors.toList());
    }
    // END
}

