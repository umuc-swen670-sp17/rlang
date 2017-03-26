/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.teamtwo.r.filetype;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Project",
        id = "org.teamtwo.r.filetype.new2"
)
@ActionRegistration(
        displayName = "#CTL_new2"
)
@ActionReference(path = "Menu/File", position = -100)
@Messages("CTL_new2=new2")
public final class new2 implements ActionListener {

    private final DataObject context;

    public new2(DataObject context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        System.out.println("consume");
    }
}
