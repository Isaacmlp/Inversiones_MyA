package Model;

public class Rol {
    private int ID;
    private String Nombre;

        /* Getters y Setters*/
    public Rol (int ID, String Nombre) {
        this.ID = ID;
        this.Nombre = Nombre;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Rol) {
        this.Nombre = Rol;
    }
}
