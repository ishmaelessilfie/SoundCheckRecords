///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.SoundTrackRecords.Configuration;
// 
//
//
//import javax.sql.DataSource;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//@Configuration
//@Primary
//
//public class WebConfig {
//
//
//@Bean
// @ConfigurationProperties(prefix = "spring.datasource")
// public DataSource dataSource() {
//  return DataSourceBuilder.create().build();
// }
//
// @Bean
// public JdbcTemplate jdbcTemplate(@Qualifier("soundcheck") DataSource ds) {
//  return new JdbcTemplate(ds);
// }
//}
