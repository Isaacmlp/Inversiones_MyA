import Controller.InvController;
import Model.InvModel;
import View.InvView;

public class Main {
    public static void main(String[] args) {
        new InvController(new InvView(), new InvModel());
    }
}