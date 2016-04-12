package br.jus.stf.autuacao.recebimento.interfaces.tarefas;

import java.util.Date;
import java.util.List;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 23.06.2015
 */
public class TarefaDto {

    private String id;
    
    private String title;
    
    private String notes;
    
    private Date startDate;

    private String dueDate;

    private boolean completed;
    
    private boolean starred;

    private boolean important;
    
    private boolean deleted;
    
    private List<RotuloDto> tags;

	public TarefaDto(String id, String title, Date startDate, boolean completed, boolean starred, boolean important, List<RotuloDto> tags) {
		this.id = id;
		this.title = title;
		this.startDate = startDate;
		this.completed = completed;
		this.starred = starred;
		this.important = important;
		this.tags = tags;
	}
	
	public String getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public String getDueDate() {
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
	
	public boolean isDeleted() {
		return deleted;
	}
	
	public List<RotuloDto> getTags() {
		return tags;
	}

}
