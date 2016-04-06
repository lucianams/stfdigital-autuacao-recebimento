package br.jus.stf.autuacao.recebimento.domain.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import br.jus.stf.core.framework.domaindrivendesign.AggregateRoot;
import br.jus.stf.core.framework.domaindrivendesign.EntitySupport;
import br.jus.stf.core.shared.protocolo.Protocolo;
import br.jus.stf.core.shared.protocolo.ProtocoloId;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 18.12.2015
 */
@Entity
@Table(name = "REMESSA", schema = "RECEBIMENTO")
public class Remessa extends EntitySupport<Remessa, ProtocoloId> implements AggregateRoot<Remessa, ProtocoloId> {

    @EmbeddedId
    private ProtocoloId protocoloId;
    
    @Column(name = "SIG_CLASSE")
    private String classeId;
    
    @Column(name ="QTD_VOLUME")
    private Integer volumes;
    
    @Column(name = "QTD_APENSO")
    private Integer apensos;
    
    @Column(name = "TIP_FORMA_RECEBIMENTO")
    private String formaRecebimento;
    
    @Column(name = "NUM_SEDEX")
    private String numeroSedex;
    
    @Column(name = "TIP_STATUS")
	@Enumerated(EnumType.STRING)
    private Status status;
    
    @Column(name = "DSC_MOTIVO")
    private String motivo;
    
    @Column(name = "TIP_PROCESSO")
    private String tipoProcesso;

    public Remessa(Protocolo protocolo, Integer volumes, Integer apensos, String formaRecebimento, String numero, String tipoProcesso, Status status) {
    	this.protocoloId = protocolo.identity();
        this.volumes = volumes;
        this.apensos = apensos;
        this.formaRecebimento = formaRecebimento;
        this.numeroSedex = numero;
        this.tipoProcesso = tipoProcesso;
        this.status = status;
    }
    
    public Remessa() {
    	// Deve ser usado apenas pelo Hibernate, que sempre usa o construtor default antes de popular uma nova instância.
    }
    
    public void classificar(String classeId, String motivo, Status status) {
        this.classeId = classeId;
        this.motivo = motivo;
        this.status = status;
    }

    public void prepararDevolucao(Status status) {
        this.status = status;
    }

    public void concluirDevolucao(Status status) {
        this.status = status;
    }

    @Override
    public ProtocoloId identity() {
        return protocoloId;
    }
    
}
