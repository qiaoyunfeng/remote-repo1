package com.qyf.pojo;

/**
 * sessionInfo只要登录成功，就需要设置到session里面，便于系统使用
 * 
 */
public class SessionInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7145049418287239762L;
	
	private Admin user;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Admin getUser() {
		return user;
	}

	public void setUser(Admin user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "SessionInfo{" +
				"user=" + user +
				'}';
	}

	public SessionInfo(Admin user) {
		this.user = user;
	}

	public SessionInfo() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SessionInfo that = (SessionInfo) o;

		return user != null ? user.equals(that.user) : that.user == null;
	}

	@Override
	public int hashCode() {
		return user != null ? user.hashCode() : 0;
	}

}
