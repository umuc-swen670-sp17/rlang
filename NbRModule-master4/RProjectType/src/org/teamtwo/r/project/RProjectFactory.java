/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.teamtwo.r.project;

import java.io.IOException;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ProjectFactory;
import org.netbeans.spi.project.ProjectState;
import org.openide.filesystems.FileObject;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Steven
 */
@ServiceProvider(service=ProjectFactory.class)
public class RProjectFactory implements ProjectFactory
{

    public static final String PROJECT_FILE = "myFile.r";

    // specifies when a project is a project, i.e.,
    // if "myFile.txt" is present in a folder
    @Override
    public boolean isProject(FileObject fo)
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return fo.getFileObject(PROJECT_FILE) != null;
    }

    // specifies when the project will be opened; if the project exists
    @Override
    public Project loadProject(FileObject fo, ProjectState ps) throws IOException
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return isProject(fo) ? new RProject(fo, ps) : null;
    }

    @Override
    public void saveProject(Project prjct) throws IOException, ClassCastException
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
