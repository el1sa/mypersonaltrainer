package com.example.personaltrainer;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.personaltrainer.domain.User;
import com.example.personaltrainer.domain.UserRepository;
import com.example.personaltrainer.domain.Focus;
import com.example.personaltrainer.domain.FocusRepository;
import com.example.personaltrainer.domain.Workout;
import com.example.personaltrainer.domain.WorkoutRepository;

@SpringBootApplication
public class PersonaltrainerApplication {

	private static final Logger log = LoggerFactory.getLogger(PersonaltrainerApplication.class);

	@Autowired
	private UserRepository urepository;

	public static void main(String[] args) {
		SpringApplication.run(PersonaltrainerApplication.class, args);
	}

	@Bean
	public CommandLineRunner insertDemoWorkouts(UserRepository urepository, WorkoutRepository wrepository,
			FocusRepository frepository) {
		return (args) -> {

			Focus f1 = new Focus("Strength");
			Focus f2 = new Focus("Endurance");
			Focus f3 = new Focus("Recovery");
			Focus f4 = new Focus("Mobility");
			frepository.save(f1);
			frepository.save(f2);
			frepository.save(f3);
			frepository.save(f4);

			// COMMAND LINE RUNNER FOR CREATING TEST WORKOUTS

			// CONSOLE INFORMATION
			log.info("Fetching demo workouts...");
			for (Workout workout : wrepository.findAll()) {
				log.info(workout.toString());

			}

			// CREATE TEST USERS: USER/USER
			User user1 = new User("user", "$2a$10$mxeL6I4rdcDw8GNem3s8auocksGSozaqqqx2/G4REYI0lPw5sezU.",
					"user3@gmail.com", "USER");
			// ADMIN/ADMIN CREATED BUT NO AUTHORITIES
			User user2 = new User("admin", "$2a$10$2cDsb2DKRse3XPg3MDnSWe.1gxRMqmnwThnmxSbC2KrmPMqIp.DNm",
					"user4@gmail.com", "ADMIN");

			urepository.save(user1);
			urepository.save(user2);

			log.info("save couple of workouts");
			wrepository.save(new Workout("Abs", LocalDate.of(2021, 8, 14), 45, f1, user1));
			wrepository.save(new Workout("Spinning", LocalDate.of(2021, 9, 16), 75, f2, user1));
			wrepository.save(new Workout("Swimming", LocalDate.of(2021, 10, 16), 30, f3, user1));

		};
	}

}
