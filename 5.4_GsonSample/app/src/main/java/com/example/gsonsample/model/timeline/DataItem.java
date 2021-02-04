package com.example.gsonsample.model.timeline;

import com.google.gson.annotations.SerializedName;

public class DataItem {

	@SerializedName("date")
	private String date = "";

	@SerializedName("new_recovered")
	private int newRecovered;

	@SerializedName("recovered")
	private int recovered = 0;

	@SerializedName("updated_at")
	private String updatedAt = "";

	@SerializedName("new_confirmed")
	private int newConfirmed;

	@SerializedName("new_deaths")
	private int newDeaths;

	@SerializedName("active")
	private int active;

	@SerializedName("confirmed")
	private int confirmed;

	@SerializedName("deaths")
	private int deaths;

	@SerializedName("is_in_progress")
	private boolean isInProgress;
}