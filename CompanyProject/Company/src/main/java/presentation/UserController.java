package presentation;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import model.User;
import service.UserService;

@Named
@ViewScoped
public class UserController extends BaseController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -9108036903604194517L;

	@EJB
	private UserService userService;

	private User user;
	private User selectedUser;
	private String repetedPassword;
	private List<User> users;

	Boolean readonly;

	@PostConstruct
	void init() {
		readonly = false;
		user = new User();
		repetedPassword = null;
		users = userService.getAllUsers();
	}

	public Boolean getReadonly() {
		return readonly;
	}

	public void setReadonly(Boolean readonly) {
		this.readonly = readonly;
	}

	public void showAddUser() {
		readonly = false;
		user = new User();
		RequestContext.getCurrentInstance().execute("PF('addUserDlgVar').show();");
	}

	public void save() {
		String errorMsg = validate(user);
		if (errorMsg != null) {
			FacesContext.getCurrentInstance().addMessage("messageId",
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Valid error!", errorMsg));
		} else {
			User check = userService.saveUser(user);
			if (check == null) {
				FacesContext.getCurrentInstance().addMessage("messageId",
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Username already exist!", null));
			} else {
				users = userService.getAllUsers();
				RequestContext.getCurrentInstance().execute("PF('addUserDlgVar').hide();");
				RequestContext.getCurrentInstance().update("userTableForm");
				FacesContext.getCurrentInstance().addMessage("MessageId",
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucessfuly saved user.", null));
			}

		}

	}
	
	public void showEditUser() {
		if(selectedUser != null) {
			user = selectedUser;
			setReadonly(false);
			RequestContext.getCurrentInstance().execute("PF('addUserDlgVar').show();");
		} else {
			FacesContext.getCurrentInstance().addMessage("MessageId",
					new FacesMessage(FacesMessage.SEVERITY_WARN, "There is no selected user!", null));
			RequestContext.getCurrentInstance().update("userTableForm");
		}
	}

	private String validate(User user) {

		if (!user.getPassword().equals(repetedPassword)) {
			return "Check password!";
		}
		if (user.getUsername().equals("") || user.getEmail().equals("") || user.getPassword().equals("")) {
			return "Must fill all fields!";
		}
		// FIXME
		return null;
	}

	public void deleteUser() {

		if (selectedUser == null) {
			FacesContext.getCurrentInstance().addMessage("messageId",
					new FacesMessage(FacesMessage.SEVERITY_WARN, "There is no selected user!", null));
			RequestContext.getCurrentInstance().update("userTableForm");
		} else {
			userService.deleteUser(selectedUser.getId());
			users = userService.getAllUsers();
			FacesContext.getCurrentInstance().addMessage("messageId",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "User id deleted!", null));
			RequestContext.getCurrentInstance().update("userTableForm");
		}

	}

	public void showUser() {
		readonly = true;
		if (selectedUser == null) {
			FacesContext.getCurrentInstance().addMessage("messageId",
					new FacesMessage(FacesMessage.SEVERITY_WARN, "There is no selected user!", null));
			RequestContext.getCurrentInstance().update("userTableForm");
		} else {
			user = selectedUser;
			RequestContext.getCurrentInstance().execute("PF('addUserDlgVar').show();");
		}
	}

	public void onRowSelect(SelectEvent event) {
		selectedUser = (User) event.getObject();
	}

	public void onRowUnselect(UnselectEvent event) {
		selectedUser = null;
	}
	
	public void close() {
		clear();
		RequestContext.getCurrentInstance().execute("PF('addUserDlgVar').hide();");
	}

	private void clear() {
		user = null;
		readonly = false;
		repetedPassword = null;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	public String getRepetedPassword() {
		return repetedPassword;
	}

	public void setRepetedPassword(String repetedPassword) {
		this.repetedPassword = repetedPassword;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
