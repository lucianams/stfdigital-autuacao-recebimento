package br.jus.stf.autuacao.recebimento.interfaces.tarefas;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.task.DelegationState;
import org.activiti.engine.task.Task;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 23.06.2015
 */
public class TarefaDto {

    private String id;

    private String title;

    private String command;

    private Date startDate;

    private Date dueDate;

    private boolean completed;

    private boolean starred;

    private boolean important;

    private String notes;

    private List<RotuloDto> tags = new LinkedList<>();

    public TarefaDto(String id, Task task) {
        TaskEntity taskEntity = (TaskEntity) task;

        this.id = id;
        this.title = taskEntity.getName();
        this.command = taskEntity.getTaskDefinitionKey();
        this.startDate = taskEntity.getCreateTime();
        this.dueDate = taskEntity.getDueDate();
        this.completed = DelegationState.RESOLVED.equals(taskEntity.getDelegationState()) || taskEntity.isDeleted();
        this.starred = Boolean.TRUE.equals(taskEntity.getTaskLocalVariables().get("starred"));
        this.important = taskEntity.getPriority() > Task.DEFAULT_PRIORITY;
        this.notes = (String) taskEntity.getTaskLocalVariables().get("notes");
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCommand() {
        return command;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public boolean isStarred() {
        return starred;
    }

    public boolean isImportant() {
        return important;
    }

    public String getNotes() {
        return notes;
    }

    public List<RotuloDto> getTags() {
        return tags;
    }

}
