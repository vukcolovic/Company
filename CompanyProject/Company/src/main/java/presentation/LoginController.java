package presentation;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import model.User;
import service.UserService;

/**
 * @author Vuk Colovic
 *
 */

@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7132728523250749606L;
	
	@EJB
	private UserService userService;
	
	
	private String username;
	private String password;
	private HttpSession jsession = null;

	public LoginController() {
		super();
		this.username = new String();
		this.password = new String();
	}

	public String tryLogin() {
		User user = userService.getUserByUsernameAndPassword(username, password);
		if (user != null) {
			 jsession =  (HttpSession) FacesContext.getCurrentInstance()
						.getExternalContext().getSession(true);
			jsession.setAttribute("username", username);
			return "dashboard.xhtml?faces-redirect=true";
		} else {
			FacesContext.getCurrentInstance().addMessage("loginForm", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Wrong username or password", ""));
			return "login";
		}
	}
	
	public void logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context
				.getExternalContext().getSession(true);
		session.invalidate();
		try {
			context.getExternalContext().redirect("Login.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
}
