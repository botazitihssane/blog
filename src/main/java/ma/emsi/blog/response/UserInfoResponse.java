package ma.emsi.blog.response;
public class UserInfoResponse {
	private int id;
	private String username;
	private String email;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserInfoResponse(int id, String username, String email) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
	}

}
