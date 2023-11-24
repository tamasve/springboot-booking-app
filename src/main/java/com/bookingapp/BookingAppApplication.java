package com.bookingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * Booking Application practice project using Thymeleaf, JPA
 * 30 Apr - 9 May 2023
 * 21 Sept 2023
 * 
 * Further to-do-s and improvements:
 * 
 * 
 * - 3 values into local variables on warehousing record page to preserve them while refresh
 * - booking page (sells and buys) with complex queries using JPA
 * 
 * - MySQL instead of H2
 * 
 * - Responsivity
 *
 */

@SpringBootApplication
public class BookingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingAppApplication.class, args);
	}

}
