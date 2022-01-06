package com.rcApp.deviceAndLocationService.config;

import com.maxmind.geoip2.DatabaseReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua_parser.Parser;

import java.io.File;
import java.io.IOException;

@Configuration
public class DeviceAndLocationConfigBeans {

  @Bean
  public DatabaseReader databaseReader() throws IOException {
    final File resource = new File("src/main/resources/maxmind/GeoLite2-City.mmdb");
    return new DatabaseReader.Builder(resource).build();
  }

  @Bean
  public Parser getParser() throws IOException {
    return new Parser();
  }
}
