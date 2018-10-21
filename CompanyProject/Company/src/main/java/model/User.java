package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@NamedQueries({
	@NamedQuery(name="User.getAll", query="SELECT u FROM User u"),
	@NamedQuery(name="User.getUserById", query="SELECT u FROM User u WHERE u.id = :userId"),
	@NamedQuery(name="User.getUserByUsernameAndPassword", query = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password"),
})
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7351729135012380019L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String firstName;
	
	private String lastName;

	@Column(unique = true)
	private String username;

	private String password;

	private Date created;

	private Date updated;
	
	private String email;

	public User() {
		super();
	}

	public User(Integer id, String  firstName, String lastName, String username, String password, Date created, Date updated, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.created = created;
		this.updated = updated;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
