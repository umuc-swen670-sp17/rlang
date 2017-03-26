/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.teamtwo.r.options;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Bugtracking",
        id = "org.teamtwo.r.options.new3"
)
@ActionRegistration(
        displayName = "#CTL_new3"
)
@ActionReference(path = "Loaders/text/x-r/Actions", position = -200)
@Messages("CTL_new3=new3")
public final class new3 implements ActionListener {

    private final DataObject context;

    public new3(DataObject context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        // TODO use context
    }
}
