package com.mercdev.rybakin.rssreader.repo.utils;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;
import com.mercdev.rybakin.rssreader.repo.entities.Channel;
import com.mercdev.rybakin.rssreader.repo.entities.ChannelItem;

public class DatabaseConfigUtil extends OrmLiteConfigUtil {
	private static final Class<?>[] entities = new Class[] {
			Channel.class,
			ChannelItem.class
	};

	public static void main(String[] args) throws IOException, SQLException {
		File configFile = new File("rss-app/src/main/res/raw/ormlite_config.txt");
		if (configFile.createNewFile()) {
			System.out.println("Config file created!");
		}
		writeConfigFile(configFile, entities);
	}
}
