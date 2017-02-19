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
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.loaders.DataObject;
import org.openide.util.NbBundle.Messages;
import org.openide.util.NbPreferences;
import org.openide.util.RequestProcessor;
import org.teamtwo.r.options.RPanel;

@ActionID(
        category = "File",
        id = "org.teamtwo.r.toolbar.RActionListener"
)
@ActionRegistration(
        iconBase = "org/teamtwo/r/toolbar/Rlogo.png",
        displayName = "#CTL_RActionListener"
)
@ActionReference(path = "Toolbars/File", position = 0)
@Messages("CTL_RActionListener=RAction")
public final class RActionListener implements ActionListener
{
    private final List<DataObject> context;
    private RequestProcessor processor = null;

    public RActionListener(List<DataObject> context) {
        this.context = context;
    }


    @Override
    public void actionPerformed(ActionEvent e)
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
