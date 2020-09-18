package modul;

import duy_truyen.population;

public class showUpdate {

    private panelUpdate panel_update;

    public panelUpdate getPanel_update() {
        return panel_update;
    }

    public void setPanel_update(panelUpdate panel_update) {
        this.panel_update = panel_update;
    }

    public showUpdate() {
        panel_update = new panelUpdate();
    }

    public void updatePanel(int current, int fanal, population popu) {
        panel_update.setLable(current, fanal, popu);
    }
}
