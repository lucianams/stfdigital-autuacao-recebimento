package br.jus.stf.autuacao.recebimento.domain.model;

import static javax.persistence.CascadeType.ALL;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.Validate;

import br.jus.stf.autuacao.recebimento.domain.model.classe.ClassePeticionavel;
import br.jus.stf.autuacao.recebimento.domain.model.documento.ModeloDevolucao;
import br.jus.stf.autuacao.recebimento.domain.model.preferencia.Preferencia;
import br.jus.stf.core.framework.domaindrivendesign.AggregateRoot;
import br.jus.stf.core.framework.domaindrivendesign.EntitySupport;
import br.jus.stf.core.shared.documento.TextoId;
import br.jus.stf.core.shared.processo.TipoProcesso;
import br.jus.stf.core.shared.protocolo.Protocolo;
import br.jus.stf.core.shared.protocolo.ProtocoloId;

/**
 * @author Rodrigo Barreiros
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 18.12.2015
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIP_PROCESSO")
@Table(name = "REMESSA", schema = "RECEBIMENTO")
public abstract class Remessa extends EntitySupport<Remessa, ProtocoloId> implements AggregateRoot<Remessa, ProtocoloId> {

    @EmbeddedId
    private ProtocoloId protocoloId;
    
    @ManyToOne
    @JoinColumn(name = "SIG_CLASSE", nullable = false)
	private ClassePeticionavel classe;
    
    @Column(name ="QTD_VOLUME", nullable = false)
    private Integer volumes;
    
    @Column(name = "QTD_APENSO")
    private Integer apensos;
    
    @Column(name = "TIP_FORMA_RECEBIMENTO", nullable = false)
    @Enumerated(EnumType.STRING)
    private FormaRecebimento formaRecebimento;
    
    @Column(name = "NUM_SEDEX")
    private String numeroSedex;
    
    @Column(name = "TIP_STATUS")
	@Enumerated(EnumType.STRING)
    private Status status;
    
    @OneToOne(cascade = ALL)
    @JoinColumn(name = "SEQ_PROTOCOLO", referencedColumnName = "SEQ_PROTOCOLO", insertable = false, updatable = false)
    private Devolucao devolucao;
    
    @OneToMany(cascade = ALL)
    @JoinTable(name = "REMESSA_PREFERENCIA", schema = "RECEBIMENTO", joinColumns = @JoinColumn(name = "SEQ_PROTOCOLO", nullable = false),
		inverseJoinColumns = @JoinColumn(name = "SEQ_PREFERENCIA", nullable = false))
    private Set<Preferencia> preferencias = new HashSet<>(0);

    public Remessa() {
    	// Deve ser usado apenas pelo Hibernate, que sempre usa o construtor default antes de popular uma nova instância.
    }
    
    public Remessa(Protocolo protocolo, Integer volumes, Integer apensos, FormaRecebimento formaRecebimento, String numeroSedex, Status status) {
		Validate.notNull(protocolo, "Protocolo requerido.");
		Validate.inclusiveBetween(1, Integer.MAX_VALUE, volumes, "Volumes inválido.");
		Validate.inclusiveBetween(0, Integer.MAX_VALUE, apensos, "Apensos inválido.");
		Validate.notNull(formaRecebimento, "Forma de recebimento requerida.");
    	Validate.isTrue(formaRecebimento.exigeNumeracao() && StringUtils.isEmpty(numeroSedex.trim()),
				"Forma de recebimento exige número de sedex.");
    	Validate.notNull(status, "Status requerido.");
    	
    	this.protocoloId = protocolo.identity();
        this.volumes = volumes;
        this.apensos = apensos;
        this.formaRecebimento = formaRecebimento;
        this.numeroSedex = numeroSedex;
        this.status = status;
    }
    
    public abstract TipoProcesso tipoProcesso();
    
    public void preautuar(ClassePeticionavel classe, Set<Preferencia> preferencias, Status status) {
		Validate.notNull(classe, "Classe requerida.");
		Validate.notNull(status, "Status requerido.");
    	Validate.isTrue(!classe.preferencias().containsAll(preferencias),
				"Alguma(s) preferência(s) não pertence(m) à classe selecionada.");
    	
    	this.classe = classe;
		this.preferencias = Optional.ofNullable(preferencias).orElse(new HashSet<>(0));
    	this.status = status;
    }
    
    public void iniciarDevolucao(String motivacao, Status status) {
    	Validate.notBlank(motivacao, "Motivação requerida.");
    	Validate.notNull(status, "Status requerido.");
    	
        devolucao = new Devolucao(motivacao);
        this.status = status;
    }

    public void elaborarDevolucao(MotivoDevolucao motivo, ModeloDevolucao modelo, TextoId texto, Status status) {
    	Validate.notNull(devolucao, "O processo de devolução não está iniciado.");
    	Validate.notNull(motivo, "Motivo requerido.");
    	Validate.notNull(modelo, "Modelo requerido.");
		Validate.isTrue(!motivo.tiposDocumento().contains(modelo.tipo()),
				"O modelo e o motivo de devolução são incompatíveis.");
    	Validate.notNull(texto, "Texto requerido.");
    	Validate.notNull(status, "Status requerido.");
    	
    	devolucao = new Devolucao(devolucao.motivacao(), motivo, modelo, texto);
        this.status = status;
    }

    public void devolver(Status status) {
        this.status = status;
    }

    @Override
    public ProtocoloId identity() {
        return protocoloId;
    }
    
}
