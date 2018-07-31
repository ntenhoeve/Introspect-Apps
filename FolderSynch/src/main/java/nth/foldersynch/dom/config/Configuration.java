package nth.foldersynch.dom.config;

import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

public class Configuration {
	private String ftpServerAddress;
	private String ftpServerPort;
	private String ftpDatabaseUserName;
	private String ftpDatabasePassword;
	private String ftpDatabasePath;
	private String deviceName;

	@Order(sequenceNumber = 0)
	public String getFtpServerAddress() {
		return ftpServerAddress;
	}

	public void setFtpServerAddress(String ftpServerAddress) {
		this.ftpServerAddress = ftpServerAddress;
	}

	@Order(sequenceNumber = 10)
	public String getFtpServerPort() {
		return ftpServerPort;
	}

	public void setFtpServerPort(String ftpServerPort) {
		this.ftpServerPort = ftpServerPort;
	}

	@Order(sequenceNumber = 20)
	public String getFtpDatabaseUserName() {
		return ftpDatabaseUserName;
	}

	public void setFtpDatabaseUserName(String ftpDatabaseUserName) {
		this.ftpDatabaseUserName = ftpDatabaseUserName;
	}

	@Order(sequenceNumber = 30)
	public String getFtpDatabasePassword() {
		return ftpDatabasePassword;
	}

	public void setFtpDatabasePassword(String ftpDatabasePassword) {
		this.ftpDatabasePassword = ftpDatabasePassword;
	}

	@Order(sequenceNumber = 40)
	public String getFtpDatabasePath() {
		return ftpDatabasePath;
	}

	public void setFtpDatabasePath(String ftpDatabasePath) {
		this.ftpDatabasePath = ftpDatabasePath;
	}

	@Order(sequenceNumber = 50)
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

}
