package Model;

import java.util.Set;

public class User {
    private String Username;
    private String Password;
    private Set<Rol> Roles;
    private String Rol;

    public String getUsername() {
        return Username;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String Rol) {
        this.Rol = Rol;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

}
