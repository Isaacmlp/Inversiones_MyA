package Model;

import java.util.Set;

public class User {
    private String Username;
    private String Password;
    private Set<Rol> Roles;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public Set<Rol> getRoles() {
        return Roles;
    }

    public void setRoles(Set<Rol> Roles) {
        this.Roles = Roles;
    }

}
