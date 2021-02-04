package com.example.gsonsample.model.timeline;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TimelineResponse {

	@SerializedName("data")
	private List<DataItem> dataItems = new ArrayList<>();

	@SerializedName("_cacheHit")
	private boolean cacheHit;
}