package com.test;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

@RunWith(ConcordionRunner.class)
public class RenameMethodFromRunner {

    public String afterRename<caret>() {
        return "val";
    }
}
