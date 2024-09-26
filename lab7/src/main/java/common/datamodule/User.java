package common.datamodule;

import java.io.Serializable;

public class User implements Serializable {
	private String name;
	private String password;
	private String salt;

	public User(){}

	public User(String name, String password, String salt){
		this.name = name;
		this.password = password;
		this.salt = salt;
	}

	public User(String name, String password){
		this.name = name;
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}


	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getSalt(){
		return salt;
	}

	@Override
	public String toString() {
		return "Аккуант:" + name + "\nпароль: " + password;
	}

}
