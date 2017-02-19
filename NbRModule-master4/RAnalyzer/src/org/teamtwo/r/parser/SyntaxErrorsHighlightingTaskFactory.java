/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.teamtwo.r.parser;

import java.util.Collection;
import java.util.Collections;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.spi.SchedulerTask;
import org.netbeans.modules.parsing.spi.TaskFactory;

/**
 *
 * @author Steven
 */
public class SyntaxErrorsHighlightingTaskFactory extends TaskFactory
{

    @Override
    public Collection<? extends SchedulerTask> create(Snapshot snpsht)
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return Collections.singleton(new SyntaxErrorsHighlightingTask ());
    }
}
