/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.teamtwo.r.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import org.teamtwo.r.options.RPanel;
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;
import org.openide.util.NbPreferences;
import org.openide.util.RequestProcessor;

@ActionID(
        category = "Debug",
        id = "org.teamtwo.r.toolbar.RExecutionAction"
)

@ActionRegistration(
        iconBase = "org/teamtwo/r/toolbar/Rlogo.png",
        displayName = "#CTL_RExecutionAction"
)

@ActionReference(path = "Loaders/text/x-r/Actions", position = -100)

@Messages("CTL_RExecutionAction=RExecution")

public final class RExecutionAction implements ActionListener
{
    private final List<DataObject> context;
    private RequestProcessor processor = null;

    public RExecutionAction(List<DataObject> context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev)
    {
        String executableFilePath = NbPreferences.forModule(RPanel.class).get("RPanel.path", "");
        File file = new File(executableFilePath);

        for (DataObject dataObject : context) {
            processor = new RequestProcessor("Exec", 1, true);
            if ("Rscript".equals(file.getName())) {
                processor.execute(new ReditorRunnable(dataObject, ""));
            } else {
                processor.execute(new ReditorRunnable(dataObject, ""));
            }
        }
    }
}
