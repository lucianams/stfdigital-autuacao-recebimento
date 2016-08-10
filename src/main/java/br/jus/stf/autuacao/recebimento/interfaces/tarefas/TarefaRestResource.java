package br.jus.stf.autuacao.recebimento.interfaces.tarefas;

import static java.util.stream.Collectors.toList;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Collection;
import java.util.List;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
    /**
     * Lista todas as tarefas associadas ao usuário ou ao grupo do usuário
     * 
     * @return a lista de tarefas do autuador
     */
    @ApiOperation(value = "Lista todas as tarefas associadas ao usuário corrente")
	@RequestMapping(method = GET)
	public List<TarefaDto> tarefas() {
    	Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    	
    	List<String> candidateGroup = authorities.stream().map(authority -> authority.getAuthority()).collect(toList());
    	
        return taskService.createTaskQuery().taskCandidateGroupIn(candidateGroup).list().stream().map(this::toDto).collect(toList());
	}

	private TarefaDto toDto(Task task) {
		ProcessInstance process = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();		
		return new TarefaDto(process.getBusinessKey(), task);
	}

}
