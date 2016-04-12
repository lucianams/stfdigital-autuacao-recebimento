package br.jus.stf.autuacao.recebimento.interfaces.tarefas;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.ApiOperation;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 23.06.2015
 */
@RestController
@RequestMapping("/api/tarefas")
public class TarefaRestResource {

	@Autowired
    private RuntimeService runtimeService;
	
	@Autowired
    private TaskService taskService;
	
    @ApiOperation(value = "Lista todas as tarefas associadas ao usuário corrente")
	@RequestMapping(method = GET)
	public List<TarefaDto> tarefas() {
    	List<TarefaDto> tarefas = new LinkedList<>();
    	
        List<Task> tasks = taskService.createTaskQuery().list();
        for (Task task : tasks) {
        	ProcessInstance process = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
        	tarefas.add(toDto(process.getBusinessKey(), task));
		}
        
        return tarefas;
	}

	private TarefaDto toDto(String businessKey, Task task) {
		return new TarefaDto(businessKey, task.getName(), task.getCreateTime(), false, false, false, Arrays.asList(new RotuloDto(1L,  "Criminal", "Criminal", "red")));
	}

}
