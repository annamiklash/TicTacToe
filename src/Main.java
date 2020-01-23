import controller.Controller;
import view.MainFrame;

public class Main {

    public static void main(String[] args) {

        final MainFrame view = new MainFrame();
        final Controller controller = new Controller(view);

        view.setVisible(true);
    }
}