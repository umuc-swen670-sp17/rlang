/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.teamtwo.r.project.nodes;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.openide.util.ChangeSupport;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ui.support.NodeFactory;
import org.netbeans.spi.project.ui.support.NodeList;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.teamtwo.r.project.RProject;

import java.util.Collections;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;
import org.netbeans.api.project.ProjectUtils;
import org.openide.nodes.ChildFactory;
import org.teamtwo.r.project.RProjectCustomizerProvider;


/**
 *
 * @author Steven
 */
@NodeFactory.Registration(projectType = "org-teamtwo-r-project", position = 10)


public class TextsNodesFactory implements NodeFactory
{



    @Override
    public NodeList<?> createNodes(Project project) {
        System.out.println("filelist1");
        RProject p = project.getLookup().lookup(RProject.class);
        assert p != null;
        return new TextsNodeList(p);
    }

    private class TextsNodeList implements NodeList<Node>, PreferenceChangeListener {

        RProject project;
        private final ChangeSupport cs;
        public TextsNodeList(RProject project) {
            this.project = project;
            this.cs = new ChangeSupport(this);

        }

        //@Override

        public List<Node> keys() {
            System.out.println("filelist2");
            FileObject textsFolder = 
            //project.getProjectDirectory().getFileObject("Files");
            project.getProjectDirectory().getFileObject(".");            
            List<Node> result = new ArrayList<Node>();
            if (textsFolder != null ) {
            //    for (FileObject textsFolderFile : textsFolder.getChildren()) {                  
                    try {
                //        result.add(DataObject.find(textsFolderFile).getNodeDelegate());
                        result.add(DataObject.find(textsFolder).getNodeDelegate());
                    } catch (DataObjectNotFoundException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
            //}
            return result;
        }

        @Override
        public Node node(Node node) {
            return new FilterNode(node);
        }

        @Override
        public void addNotify() {
        }

        @Override
        public void removeNotify() {
        }

        @Override
        public void addChangeListener(ChangeListener cl) {
            cs.addChangeListener(cl);            
        }

        @Override
        public void removeChangeListener(ChangeListener cl) {
            cs.removeChangeListener(cl);            
        }
        //@Override
        private void fireChange() {
            cs.fireChange();
        }   

        @Override
        public void preferenceChange(PreferenceChangeEvent evt) {
            if (evt.getKey().equals("showMyNode")) {
                fireChange();
            }
        }
    }
}


