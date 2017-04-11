package com.mercdev.rybakin.rss.engine;

public class Cloud {
	private final String domain;
	private final String port;
	private final String path;
	private final String registerProcedure;
	private final String protocol;

	public Cloud(String domain, String port, String path, String registerProcedure, String protocol) {
		this.domain = domain;
		this.port = port;
		this.path = path;
		this.registerProcedure = registerProcedure;
		this.protocol = protocol;
	}

	public String getDomain() {
		return domain;
	}

	public String getPort() {
		return port;
	}

	public String getPath() {
		return path;
	}

	public String getRegisterProcedure() {
		return registerProcedure;
	}

	public String getProtocol() {
		return protocol;
	}

	@Override
	public String toString() {
		return "Cloud{" +
				"domain='" + domain + '\'' +
				", port='" + port + '\'' +
				", path='" + path + '\'' +
				", registerProcedure='" + registerProcedure + '\'' +
				", protocol='" + protocol + '\'' +
				'}';
	}
}
