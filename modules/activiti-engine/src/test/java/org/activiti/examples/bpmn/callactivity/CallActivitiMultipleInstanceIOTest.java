package org.activiti.examples.bpmn.callactivity;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**
 * @author Saeid Mirzaei
 */
public class CallActivitiMultipleInstanceIOTest  extends PluggableActivitiTestCase  {
	@Deployment(resources={
		    "org/activiti/examples/bpmn/callactivity/multiInstanceCallerProcess.bpmn20.xml",
		    "org/activiti/examples/bpmn/callactivity/multiInstanceSubProcess.bpmn20.xml"       
		  })
	
	// this test case sends a "Hello" string and passes it to subprocess in "var1" variable. 
	// Subprocess sets variable "var2" to var1 + " world". It is returned back to caller by activiti:out and checked there.
	 public void testMultipleWithoutIO() {
		
		String randomStringPatter = "Hello";

		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("var1", randomStringPatter);
		
		runtimeService.startProcessInstanceByKey("multiInstanceCallerProcess", vars);
		
		Task task = taskService.createTaskQuery().singleResult();
		assertNotNull(task);
		String taskId = task.getId();
		
		
		String var1 = (String) taskService.getVariable(taskId, "var1");
		String var2 = (String) taskService.getVariable(taskId, "var2");
		
		assertEquals(randomStringPatter, var1);
		assertEquals("Hello World!", var2);
		
		taskService.complete(task.getId());
		

	}

}
