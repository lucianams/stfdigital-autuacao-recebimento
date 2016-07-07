package br.jus.stf.autuacao.recebimento.domain.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.apache.commons.lang3.Validate;

import br.jus.stf.autuacao.recebimento.domain.model.classe.ClassePeticionavel;
import br.jus.stf.autuacao.recebimento.domain.model.preferencia.Preferencia;
import br.jus.stf.core.shared.processo.Sigilo;
import br.jus.stf.core.shared.processo.TipoProcesso;
import br.jus.stf.core.shared.protocolo.Protocolo;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 14.04.2016
 */
@Entity
@DiscriminatorValue("RECURSAL")
public class RemessaRecursal extends Remessa {
	
	@Column(name = "NUM_PROCESSO_ORIGEM")
	private String numeroProcessoOrigem;
	
	@Column(name = "NUM_UNICO_PROCESSO")
	private String numeroUnicoProcesso;
	
	public RemessaRecursal() {
    	// Deve ser usado apenas pelo Hibernate, que sempre usa o construtor default antes de popular uma nova instância.
    }
    
	/**
	 * @param protocolo
	 * @param volumes
	 * @param apensos
	 * @param formaRecebimento
	 * @param numeroSedex
	 * @param sigilo
	 * @param recebedor
	 * @param status
	 */
	public RemessaRecursal(Protocolo protocolo, Integer volumes, Integer apensos, FormaRecebimento formaRecebimento,
			String numeroSedex, Sigilo sigilo, Recebedor recebedor, Status status) {
		super(protocolo, volumes, apensos, formaRecebimento, numeroSedex, sigilo, recebedor, status);
    }
	
	/**
	 * @param classe
	 * @param preferencias
	 * @param sigilo
	 * @param numeroProcessoOrigem
	 * @param numeroUnicoProcesso
	 * @param status
	 */
	public void preautuar(ClassePeticionavel classe, Set<Preferencia> preferencias, Sigilo sigilo,
			String numeroProcessoOrigem, String numeroUnicoProcesso, Status status) {
		super.preautuar(classe, preferencias, sigilo, status);
		
		Validate.notBlank(numeroProcessoOrigem, "Número na origem requerido.");
		Validate.notBlank(numeroUnicoProcesso, "Número único requerido.");
		
		this.numeroProcessoOrigem = numeroProcessoOrigem;
		this.numeroUnicoProcesso = numeroUnicoProcesso;
    }
    
    @Override
	public TipoProcesso tipoProcesso() {
    	return TipoProcesso.RECURSAL;
    }
    
}
