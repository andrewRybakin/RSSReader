package com.mercdev.rybakin.rssreader.tasks;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.SpiceService;
import com.octo.android.robospice.UncachedSpiceService;

public class TaskManager extends SpiceManager {
	private static TaskManager instance;

	/**
	 * Creates a {@link SpiceManager}. Typically this occurs in the construction
	 * of an Activity or Fragment. This method will check if the service to bind
	 * to has been properly declared in AndroidManifest.
	 *
	 * @param spiceServiceClass
	 * 		the service class to bind to.
	 */
	private TaskManager(Class<? extends SpiceService> spiceServiceClass) {
		super(spiceServiceClass);
	}

	public static TaskManager getInstance() {
		if (instance == null) {
			instance = new TaskManager(UncachedSpiceService.class);
		}
		return instance;
	}

}
