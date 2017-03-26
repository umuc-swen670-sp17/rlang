/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.teamtwo.r.project;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JPanel;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;
import org.netbeans.spi.project.ui.CustomizerProvider;
import org.netbeans.spi.project.ui.support.CommonProjectActions;
import org.netbeans.spi.project.ui.support.DefaultProjectOperations;
import org.netbeans.spi.project.ui.support.ProjectCustomizer;
import org.openide.awt.StatusDisplayer;
import org.openide.filesystems.FileObject;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Steven
 */
public class RProjectCustomizerProvider implements CustomizerProvider
{

        private static class PanelProvider implements ProjectCustomizer.CategoryComponentProvider {
        private Map panels;
        
        private JPanel EMPTY_PANEL = new JPanel();
        
        public PanelProvider(Map panels) {
            this.panels = panels;
        }
        
        public JComponent create(ProjectCustomizer.Category category) {
            JComponent panel = (JComponent) panels.get(category);
            return panel == null ? EMPTY_PANEL : panel;
        }
        
    }
        
        
    public final RProject project;

    public static final String CUSTOMIZER_FOLDER_PATH = "Projects/org-teamtwo-r-project/Customizer";
    private ProjectCustomizer.Category[] categories;
    private ProjectCustomizer.CategoryComponentProvider panelProvider;
    
    // Names of categories
    private static final String IMPORTANT_FILES_CATEGORY = "ImpFilesCategory";
    private static final String BASICS_FILES_CATEGORY = "BasicsFilesCategory";
    
    public RProjectCustomizerProvider(RProject project)
    {
        this.project = project;
    }

    private void init() {
        ProjectCustomizer.Category importantFiles = ProjectCustomizer.Category.create(
                    IMPORTANT_FILES_CATEGORY,
                    "Important Files",
                    null,
                    null);

            ProjectCustomizer.Category basicsFiles = ProjectCustomizer.Category.create(
                    BASICS_FILES_CATEGORY,
                    "Plugin Basics",
                    null,
                    null);

            categories = new ProjectCustomizer.Category[] {
                importantFiles,
                basicsFiles
            };
        
        Map panels = new HashMap();
        //panels.put(importantFiles, new ImportantFilesVisual());
       // panels.put(basicsFiles, new BasicsVisual());
        
        panelProvider = new PanelProvider(panels);
    }    
    

    @Override
    public void showCustomizer()
    {
        init();

        OptionListener listener = new OptionListener(project);
        Dialog dialog = ProjectCustomizer.createCustomizerDialog(categories, panelProvider,
        null, listener, null);
        dialog.addWindowListener(listener);

        dialog.setTitle(ProjectUtils.getInformation(project).getDisplayName());

        dialog.setVisible(true);        

    }

    private class OKOptionListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            StatusDisplayer.getDefault().setStatusText("OK button clicked for "
                    + project.getProjectDirectory().getName() + " customizer!");
        }
    }
    
private class OptionListener extends WindowAdapter implements ActionListener {
    private Project project;
        
    OptionListener(Project project) {
        this.project = project;
    }
        
    public void actionPerformed(ActionEvent e) {
        try {
            CommonProjectActions.newFileAction();
            CommonProjectActions.closeProjectAction();
            //DefaultProjectOperations.performDefaultRenameOperation(project, "");            
            System.out.println("actions2");                
        } catch (Exception ex){
            System.out.println("actions2fail"); 
        }

        // Close and dispose the dialog
    }

    public void windowClosed(WindowEvent e) {
        try {
            CommonProjectActions.newFileAction();
            CommonProjectActions.closeProjectAction();
            FileObject newfile=project.getProjectDirectory().createData("NewSource4", "java");            
            DefaultProjectOperations.performDefaultRenameOperation(project, project.getProjectDirectory().getName());            
            System.out.println("actions2b");
            //FileObject subfolder = project.getProjectDirectory().createFolder("sub");

        } catch (Exception ex){
            System.out.println("actions2bfail"); 
        }
    }

    public void windowClosing(WindowEvent e) {
        // Close and dispose the dialog
    }
}    
}
