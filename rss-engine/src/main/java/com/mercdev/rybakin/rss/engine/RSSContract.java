package com.mercdev.rybakin.rss.engine;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

final class RSSContract {
	static final String RSS_ROOT = "rss";
	static final String RSS_VERSION_ATTR = "version";
	static final String RSS_SUPPORTED_VERSION = "2.0";

	static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.getDefault());

	static final class Channel {
		static final String ROOT_TAG = "channel";

		static final String TITLE_TAG = "title";
		static final String LINK_TAG = "link";
		static final String DESCRIPTION_TAG = "description";
		static final Map<String, TagInfo> TAGS = new HashMap<>();

		static {
			TAGS.put("category", new TagInfo("addCategory"));
			TAGS.put("copyright", new TagInfo("setCopyright"));
			TAGS.put("docs", new TagInfo("setDocs"));
			TAGS.put("generator", new TagInfo("setGenerator"));
			TAGS.put("language", new TagInfo("setLanguage"));
			TAGS.put("lastBuildDate", new TagInfo("setLastBuildDate"));
			TAGS.put("managingEditor", new TagInfo("setManagingEditor"));
			TAGS.put("pubDate", new TagInfo("setPubDate"));
			TAGS.put("rating", new TagInfo("setRating"));
			TAGS.put("ttl", new TagInfo("setTTL"));
			TAGS.put("webMaster", new TagInfo("setWebMaster"));

			TAGS.put("cloud", new TagInfo(true));
			TAGS.put("image", new TagInfo(true));
			TAGS.put("item", new TagInfo(true));
			TAGS.put("skipDays", new TagInfo(true));
			TAGS.put("skipHours", new TagInfo(true));
			TAGS.put("textInput", new TagInfo(true));
		}
	}

	static final class Cloud {
		static final String ROOT_TAG = "cloud";

		static final String DOMAIN_ATTR = "domain";
		static final String PORT_ATTR = "port";
		static final String PATH_ATTR = "path";
		static final String REGISTER_PROCEDURE_ATTR = "registerProcedure";
		static final String PROTOCOL_ATTR = "protocol";
	}

	static final class Image {
		static final String ROOT_TAG = "image";

		static final String URL = "url";
		static final String TITLE = "title";
		static final String LINK = "link";
		static final String WIDTH = "width";
		static final String HEIGHT = "height";
		static final String DESCRIPTION = "description";
	}

	static final class TextInput {
		static final String ROOT_TAG = "textInput";

		static final String TITLE = "title";
		static final String DESCRIPTION = "description";
		static final String NAME = "name";
		static final String LINK = "link";
	}

	static final class SkipDays {
		static final String ROOT_TAG = "skipDays";

		static final String DAY = "day";
	}

	static final class SkipHours {
		static final String ROOT_TAG = "skipHours";

		static final String HOUR = "hour";
	}

	static final class Item {
		static final String ROOT_TAG = "item";

		static final String TITLE = "title";
		static final String DESCRIPTION = "description";

		static final Map<String, TagInfo> TAGS = new HashMap<>();

		static {
			TAGS.put("author", new TagInfo("setAuthor"));
			TAGS.put("category", new TagInfo("addCategory"));
			TAGS.put("comments", new TagInfo("setComments"));
			TAGS.put("enclosure", new TagInfo(true));
			TAGS.put("guid", new TagInfo("setGuid"));
			TAGS.put("pubDate", new TagInfo("setPubDate"));
			TAGS.put("source", new TagInfo(true));
		}

		static final class Enclosure {
			static final String ROOT_TAG = "enclosure";

			static final String URL_ATTR = "url";
			static final String LENGTH_ATTR = "length";
			static final String TYPE_ATTR = "type";
		}

		static final class Source {
			static final String ROOT_TAG = "source";

			static final String URL_ATTR = "url";
		}
	}

	static final class TagInfo {
		private final String setter;
		private final boolean hasChildren;

		TagInfo(String setter) {
			this.setter = setter;
			this.hasChildren = false;
		}

		TagInfo(boolean hasChildren) {
			this.setter = null;
			this.hasChildren = hasChildren;
		}

		String getSetter() {
			return setter;
		}

		boolean isHasChildren() {
			return hasChildren;
		}
	}
}
