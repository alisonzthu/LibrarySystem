package Nov16LibrarySystem.UIPackage;

import javax.swing.*;
import java.awt.*;

/**
 * Created by macstudio on 11/21/16.
 */
public class CreateBorrowBooks {
    public JPanel borrowBooks() {
        JPanel maincard6Panel = new JPanel(new GridBagLayout());
        JPanel upPanel = new HomeNlogoutPanel().addOutmostPanel();
        maincard6Panel.add(upPanel, new GBC(0, 0).setAnchor(GBC.WEST));

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel card1 = new JPanel(new GridBagLayout());
        JPanel card2 = new JPanel(new GridBagLayout());
        JPanel card3 = new JPanel(new GridBagLayout());
        final String BRWBOOK = "Borrow Book";
        final String BRWLIST = "Borrower's list";
        final String RETURNBK = "Return book";


        tabbedPane.add(BRWBOOK, card1);
        tabbedPane.add(BRWLIST, card2);
        tabbedPane.add(RETURNBK, card3);
        tabbedPane.setPreferredSize(new Dimension(700, 250));

        maincard6Panel.add(tabbedPane, new GBC(0, 1).setAnchor(GBC.CENTER));
        return maincard6Panel;
    }
}
