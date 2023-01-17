package edu.gonzaga;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import javax.swing.*;

public class DieButton extends JButton implements PropertyChangeListener {
    private Die currentDie;
    private final DieFaces faces;
    private boolean locked;
    private boolean rolled;

    public DieButton(int position) throws IOException {
        super();
        faces = new DieFaces();
        rolled = false;
        setPosition(position);
        setVisible(true);
    }

    private void setPosition(int position) {
        int x = 0, y = 0;
        int width = 100, height = 100;
        switch (position) {
            case 0:
                x = 15;
                y = 5;
                break;
            case 1:
                x = 15;
                y = 115;
                break;
            case 2:
                x = 15;
                y = 225;
                break;
            case 3:
                x = 15;
                y = 335;
                break;
            case 4:
                x = 15;
                y = 445;
                break;
            default:
                break;
        }
        setBounds(x, y, width, height);
    }

    private void unlock() {
        locked = false;
        currentDie.unlock();
        setBorder(BorderFactory.createEmptyBorder());
    }

    private void toggleLocked() {
        locked = !locked;
        if (locked) {
            currentDie.lock();
            setBorder(BorderFactory.createLineBorder(Color.RED));
        } else {
            currentDie.unlock();
            setBorder(BorderFactory.createEmptyBorder());
        }
    }

    public void attachDie(Die dieToLink) {
        this.currentDie = dieToLink;
        currentDie.addPropertyChangeListener(this);
        addActionListener(
                e -> {
                    if (rolled) toggleLocked();
                });
        setIcon(faces.getFace(currentDie.getSideUp()));
    }

    public void updateDie(Die dieToUpdate) {
        this.currentDie = dieToUpdate;
        currentDie.addPropertyChangeListener(this);
        setIcon(faces.getFace(currentDie.getSideUp()));
    }

    public void allowLocking() {
        rolled = true;
    }

    public void reset() {
        currentDie.setDie(1);
        unlock();
        rolled = false;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();
        if ("newSide".equals(propertyName)) setIcon(faces.getFace((int) evt.getNewValue()));
    }
}
