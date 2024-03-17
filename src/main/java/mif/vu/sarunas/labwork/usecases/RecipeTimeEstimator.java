package mif.vu.sarunas.labwork.usecases;

import mif.vu.sarunas.labwork.interceptors.LogPerformance;
import mif.vu.sarunas.labwork.services.PreparationTimeEstimator;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Model
@SessionScoped
@LogPerformance
public class RecipeTimeEstimator implements Serializable {
	@Inject
	private PreparationTimeEstimator timeEstimator;

	private final Map<Long, CompletableFuture<LocalTime>> timeEstimatorTasks = new HashMap<>();

	private final Map<Long, String> timeEstimationStatus = new HashMap<>();

	public String estimatePreparationTime(Long recipeId) throws ExecutionException, InterruptedException {
		this.timeEstimatorTasks.put(recipeId, CompletableFuture.supplyAsync(() -> timeEstimator.estimateRecipePreparationTime()));
		return this.refreshStatus(recipeId);
	}

	public String refreshStatus(Long recipeId) throws ExecutionException, InterruptedException {
		if (timeEstimatorTasks.get(recipeId) == null) {
			timeEstimationStatus.put(recipeId, "Time estimation has not yet been started.");
		} else if (!timeEstimatorTasks.get(recipeId).isDone()) {
			timeEstimationStatus.put(recipeId, "Time estimation is currently in-progress.");
		} else {
			timeEstimationStatus.put(recipeId, "The recipe should take about " + timeEstimatorTasks.get(recipeId).get().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
		}

		return "/recipe?recipeId=" + recipeId;
	}

	public String getTimeEstimationStatus(Long recipeId) {
		return timeEstimationStatus.getOrDefault(recipeId, "Time estimation has not yet been started");
	}
}
