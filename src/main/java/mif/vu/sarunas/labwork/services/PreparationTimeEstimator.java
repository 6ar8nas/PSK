package mif.vu.sarunas.labwork.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.concurrent.ThreadLocalRandom;

@ApplicationScoped
public class PreparationTimeEstimator implements Serializable {

	public LocalTime estimateRecipePreparationTime() {
		try {
			Thread.sleep(3000); // Simulate intensive work
		} catch (InterruptedException e) {
			// Intentionally empty
		}
		int hour = ThreadLocalRandom.current().nextInt(0, 24); // 0-23
		int minute = ThreadLocalRandom.current().nextInt(0, 60); // 0-59
		int second = ThreadLocalRandom.current().nextInt(0, 60); // 0-59
		return LocalTime.of(hour, minute, second);
	}
}
