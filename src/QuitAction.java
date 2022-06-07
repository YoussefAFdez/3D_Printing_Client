import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class QuitAction extends AbstractAction {
    public QuitAction() {
        super("Quit");
        this.putValue(SHORT_DESCRIPTION, "Exits the program");
        putValue(MNEMONIC_KEY, KeyEvent.VK_Q);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
