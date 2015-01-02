package org.activiti.engine.test.jobexecutor;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;


public class RetryBpmnErrorIndicator implements JavaDelegate {
  static boolean isCalled = false;
  
  public static void reset() {
    isCalled = false;
    
  }
  
  public static boolean isCalled() {
    return isCalled;
  }

  @Override
  public void execute(DelegateExecution execution) throws Exception {
   isCalled = true;
    
  }
  
  
  

}
