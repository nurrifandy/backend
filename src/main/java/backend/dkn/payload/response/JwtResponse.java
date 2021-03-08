package backend.dkn.payload.response;

import backend.dkn.model.UserDetailModel;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Integer id;
	private String username;
	private UserDetailModel user;

	public JwtResponse(String accessToken, Integer id, String username, UserDetailModel user) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.user = user;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * @return the users
	 */
	public UserDetailModel getUser() {
		return user;
	}

	/**
	 * @param users the users to set
	 */
	public void setUser(UserDetailModel user) {
		this.user = user;
	}

}
