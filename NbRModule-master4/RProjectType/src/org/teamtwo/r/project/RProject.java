/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.teamtwo.r.project;

import java.awt.Image;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.netbeans.api.annotations.common.StaticResource;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectInformation;
import org.netbeans.spi.project.ActionProvider;
import org.netbeans.spi.project.CopyOperationImplementation;
import org.netbeans.spi.project.DeleteOperationImplementation;
import org.netbeans.spi.project.MoveOrRenameOperationImplementation;
import org.netbeans.spi.project.ProjectState;
import org.netbeans.spi.project.support.LookupProviderSupport;
import org.netbeans.spi.project.ui.LogicalViewProvider;
import org.netbeans.spi.project.ui.support.CommonProjectActions;
import org.netbeans.spi.project.ui.support.DefaultProjectOperations;
import org.netbeans.spi.project.ui.support.NodeFactorySupport;
import org.netbeans.spi.project.ui.support.UILookupMergerSupport;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataFolder;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.Utilities;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ProxyLookup;

/**
 *
 * @author Steven
 */
public class RProject implements Project
{

    private final FileObject projectDir;
    private final ProjectState state;
    private Lookup lkp;

    public RProject(FileObject dir, ProjectState state)
    {
        this.projectDir = dir;
        this.state = state;
    }

    @Override
    public FileObject getProjectDirectory()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return projectDir;
    }

    @Override
    public Lookup getLookup()
    {
        //System.out.println("getLookup1");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (lkp == null)
        {
            lkp = Lookups.fixed(new Object[]
            {
                this,
                new RPInformation(),
                new RProjectLogicalView(this),
                new RProjectCustomizerProvider(this),
                new RProjectActionProvider(),
                new RProjectMoveOrRenameOperation(this),
                new RProjectCopyOperation(),
                new RProjectDeleteOperation(this),
                UILookupMergerSupport.createPrivilegedTemplatesMerger(),
                UILookupMergerSupport.createRecommendedTemplatesMerger()
            });
        }
        return LookupProviderSupport.createCompositeLookup(lkp, "Projects/org-teamtwo-r-project/Lookup");
    }

    //Action Provider for the project
    class RProjectActionProvider implements ActionProvider
    {

        @Override
        public String[] getSupportedActions()
                
        {
                    System.out.println("getsupportedactions1");
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return new String[]
            {
                ActionProvider.COMMAND_RENAME,
                ActionProvider.COMMAND_MOVE,
                ActionProvider.COMMAND_COPY,
                ActionProvider.COMMAND_DELETE
            };
        }

        @Override
        public void invokeAction(String command, Lookup context) throws IllegalArgumentException
        {
                            System.out.println("invokeactions1");
            if (command.equalsIgnoreCase(ActionProvider.COMMAND_RENAME))
            {
                DefaultProjectOperations.performDefaultRenameOperation(RProject.this, "");
                System.out.println("rename");
            }
            if (command.equalsIgnoreCase(ActionProvider.COMMAND_MOVE))
            {
                DefaultProjectOperations.performDefaultMoveOperation(RProject.this);
            }
            if (command.equalsIgnoreCase(ActionProvider.COMMAND_COPY))
            {
                DefaultProjectOperations.performDefaultCopyOperation(RProject.this);
            }
            if (command.equalsIgnoreCase(ActionProvider.COMMAND_DELETE))
            {
                DefaultProjectOperations.performDefaultDeleteOperation(RProject.this);
            }
        }

        @Override
        public boolean isActionEnabled(String command, Lookup context) throws IllegalArgumentException
        {
                            System.out.println("actionisenabled");
            if ((command.equals(ActionProvider.COMMAND_RENAME)))
            {
                return true;
            } else if ((command.equals(ActionProvider.COMMAND_MOVE)))
            {
                return true;
            } else if ((command.equals(ActionProvider.COMMAND_COPY)))
            {
                return true;
            } else if ((command.equals(ActionProvider.COMMAND_DELETE)))
            {
                return true;
            }
            return false;
        }
    }

    //Move operation implementation
    private final class RProjectMoveOrRenameOperation implements MoveOrRenameOperationImplementation
    {

        private final RProject rProject;

        public RProjectMoveOrRenameOperation(RProject rProject)
        {
            this.rProject = rProject;
        }

        @Override
        public List<FileObject> getMetadataFiles()
        {
            FileObject projectDirectory = rProject.getProjectDirectory();
            List<FileObject> files = new ArrayList<FileObject>();
            for (FileObject nomFichier : files)
            {
                System.out.println("========" + nomFichier.getName() + "============");
            }
                System.out.println("rename2");            
            return files;

        }

        @Override
        public List<FileObject> getDataFiles()
        {
                            System.out.println("rename3");
            return new ArrayList<FileObject>();
        }

        @Override
        public void notifyRenaming() throws IOException
        {
        }

        @Override
        public void notifyRenamed(String nueName) throws IOException
        {
        }

        @Override
        public void notifyMoving() throws IOException
        {
        }

        @Override
        public void notifyMoved(Project original, File originalPath, String nueName) throws IOException
        {
        }
    }

//Class information of the project
    private final class RPInformation implements ProjectInformation
    {

        @StaticResource()
        public static final String RPROJECT_ICON = "org/teamtwo/r/project/Rlogo.png";

        @Override
        public String getName()
        {
                            System.out.println("rpinformationgetname");
            return getProjectDirectory().getName();
        }

        @Override
        public String getDisplayName()
        {
            return getName();
        }

        @Override
        public Icon getIcon()
        {
            return new ImageIcon(ImageUtilities.loadImage(RPROJECT_ICON));
        }

        @Override
        public Project getProject()
        {
            return RProject.this;
        }

        @Override
        public void addPropertyChangeListener(PropertyChangeListener pl)
        {

        }

        @Override
        public void removePropertyChangeListener(PropertyChangeListener pl)
        {

        }

    }

    //Copy operation implementation
    private final class RProjectCopyOperation implements CopyOperationImplementation
    {

        @Override
        public List<FileObject> getMetadataFiles()
        {
            return new ArrayList<FileObject>();
        }

        @Override
        public List<FileObject> getDataFiles()
        {
            return new ArrayList<FileObject>();
        }

        @Override
        public void notifyCopying() throws IOException
        {
        }

        @Override
        public void notifyCopied(Project prjct, File file, String string) throws IOException
        {
        }

    }

    //Delete operation implementation
    private final class RProjectDeleteOperation implements DeleteOperationImplementation
    {
        private final RProject rProject;        
        public RProjectDeleteOperation(RProject rProject)
        {
            this.rProject = rProject;
        }

        @Override
        public List<FileObject> getMetadataFiles()
        {

            FileObject projectDirectory = rProject.getProjectDirectory().getFileObject(".");
            List<FileObject> files = new ArrayList<FileObject>();
            //return new ArrayList<FileObject>();    
            return files;

        }

        @Override
        public List<FileObject> getDataFiles()
        {
            FileObject projectDirectory = rProject.getProjectDirectory().getFileObject(".");
            List<FileObject> files = new ArrayList<FileObject>();            
            //return new ArrayList<FileObject>();
            return files;
        }

        @Override
        public void notifyDeleting() throws IOException
        {
        }

        @Override
        public void notifyDeleted() throws IOException
        {
        }
    }

    //Logical view of the project
    class RProjectLogicalView implements LogicalViewProvider
    {

        @StaticResource()
        public static final String RPROJECT_ICON = "org/teamtwo/r/project/Rlogo.png";

        private final RProject project;

        public RProjectLogicalView(RProject project)
        {
            System.out.println("logicalview");  
            this.project = project;
        }

        @Override
        public Node createLogicalView()
        {
            try
            {
                // obtain the project directory node
                            System.out.println("logicalview2");  
                FileObject projectDirectory = project.getProjectDirectory();
                DataFolder projectFolder = DataFolder.findFolder(projectDirectory);
                Node nodeOfProjectFolder = projectFolder.getNodeDelegate();
                return new ProjectNode(nodeOfProjectFolder, project);
            } catch (DataObjectNotFoundException ex)
            {
                Exceptions.printStackTrace(ex);
                System.out.println("logicalview3"); 
                return new AbstractNode(Children.LEAF);
            }
        }

        private final class ProjectNode extends FilterNode
        {

            final RProject project;

            public ProjectNode(Node node, RProject project) throws DataObjectNotFoundException
            {
                super(node,
                        NodeFactorySupport.createCompositeChildren(project, "Projects/org-teamtwo-r-project/Nodes"),
                        //new FilterNode.Children(original),
                        new ProxyLookup(
                                new Lookup[]
                                {
                                    Lookups.singleton(project),
                                    node.getLookup()
                                }));
                                System.out.println("logicalview4"); 
                this.project = project;
            }

            @Override

            public Action[] getActions(boolean arg0)
            {
                System.out.println("actions1");
                
                return new Action[]
                {
                    //CommonProjectActions.newFileAction(),
                    CommonProjectActions.copyProjectAction(),
                    CommonProjectActions.renameProjectAction(),
                    CommonProjectActions.deleteProjectAction(),
                    CommonProjectActions.moveProjectAction(),
                   // CommonProjectActions.newProjectAction(),
                   // CommonProjectActions.setAsMainProjectAction(),
                   // CommonProjectActions.setProjectConfigurationAction(),
                   // CommonProjectActions.customizeProjectAction(),
                    CommonProjectActions.closeProjectAction()
                    //Utilities.actionsForPath("Actions/Edit/")
                    //CommonProjectActions.forType()
                };
            }

            @Override
            public Image getIcon(int type)
            {
                return ImageUtilities.loadImage(RPROJECT_ICON);
            }

            @Override
            public Image getOpenedIcon(int type)
            {
                return getIcon(type);
            }

            @Override
            public String getDisplayName()
            {
                return project.getProjectDirectory().getName();
            }

        }

        @Override
        public Node findPath(Node node, Object o)
        {
            return null;
        }

    }

}
