
package com.mycompany.labeller.javafx.client.tabs;

import javafx.scene.control.Tab;

/**
 *
 * @author ador
 */
public abstract class BaseLabellerTab extends Tab {

    private final boolean authenticationRequired;
    
    public BaseLabellerTab(String label, boolean authenticationRequired) {
        super(label);
        this.authenticationRequired = authenticationRequired;
        this.setClosable(false);
        initGui();
    }

    public boolean isAuthenticationRequired() {
        return authenticationRequired;
    }
    
    public abstract void initGui();
}
