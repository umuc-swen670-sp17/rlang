/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.netbeans.spi.project.ProjectState;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileSystem;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.teamtwo.r.project.RProject;
import org.teamtwo.r.project.RProjectFactory;
import org.teamtwo.r.project.nodes.TextsNodesFactory;

/**
 *
 * @author matth
 */
public class TextsNodes1 {
    
    public TextsNodes1() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    private static final String MANIFEST_CONTENT =
            "Manifest-Version: 1.0\n" +
            "Java-Bean: true\n" +
            "OpenIDE-Module-Name: com.foo.bar\n\n";

 

    private void writeFile(String content, FileObject file) throws Exception {
        OutputStream os = file.getOutputStream();
        os.write(content.getBytes("UTF-8"));
        os.close();
    }
    
    @Test
    public void OpenFileCheck() throws Exception {
        FileSystem fs = FileUtil.createMemoryFileSystem();
        FileObject mani = fs.getRoot().createData("mani.mf");
        writeFile(MANIFEST_CONTENT, mani);
        DataObject Dob = DataObject.find(mani);   
        FileObject fileobject = Dob.getPrimaryFile();
        ProjectState state = null;
        RProject rproject = new RProject(fileobject,state);
        TextsNodesFactory tnp = new TextsNodesFactory();
        assertNotNull("should not be null", tnp.createNodes(rproject));
      
    }

    public void CloseFileCheck() throws Exception {
        FileSystem fs = FileUtil.createMemoryFileSystem();
        FileObject mani = fs.getRoot().createData("mani.mf");
        writeFile(MANIFEST_CONTENT, mani);
        DataObject Dob = DataObject.find(mani);   
        FileObject fileobject = Dob.getPrimaryFile();
        ProjectState state = null;
        RProject rproject = new RProject(fileobject,state);
        TextsNodesFactory tnp = new TextsNodesFactory();
        assertNotNull("should not be null", tnp.createNodes(rproject));    
      
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
