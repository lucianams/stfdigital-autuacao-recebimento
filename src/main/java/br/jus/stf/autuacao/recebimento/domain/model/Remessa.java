package br.jus.stf.autuacao.recebimento.domain.model;

import static java.util.Comparator.comparing;
import static javax.persistence.CascadeType.ALL;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
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

import br.jus.stf.autuacao.recebimento.domain.model.documento.ModeloDevolucao;
import br.jus.stf.autuacao.recebimento.domain.model.suportejudicial.ClassePeticionavel;
import br.jus.stf.autuacao.recebimento.domain.model.suportejudicial.Preferencia;
import br.jus.stf.core.framework.domaindrivendesign.AggregateRoot;
import br.jus.stf.core.framework.domaindrivendesign.DomainEvent;
import br.jus.stf.core.framework.domaindrivendesign.EntitySupport;
import br.jus.stf.core.shared.documento.TextoId;
import br.jus.stf.core.shared.eventos.RecebimentoFinalizado;
import br.jus.stf.core.shared.eventos.RemessaRegistrada;
import br.jus.stf.core.shared.processo.Sigilo;
import br.jus.stf.core.shared.processo.TipoProcesso;
import br.jus.stf.core.shared.protocolo.Numero;
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
    
    @Embedded
    @AttributeOverrides( {
        @AttributeOverride(name="numero", column = @Column(name="NUM_REMESSA", nullable = false)),
        @AttributeOverride(name="ano", column = @Column(name="NUM_ANO", nullable = false))
    })
    private Numero numero;
    
    @Column(name ="QTD_VOLUME", nullable = false)
    private Integer volumes;
    
    @Column(name = "QTD_APENSO")
    private Integer apensos;
    
    @Column(name = "TIP_FORMA_RECEBIMENTO", nullable = false)
    @Enumerated(EnumType.STRING)
    private FormaRecebimento formaRecebimento;
    
    @Column(name = "NUM_SEDEX")
    private String numeroSedex;
    
    @Column(name = "TIP_STATUS", nullable = false)
	@Enumerated(EnumType.STRING)
    private Status status;
    
    @OneToOne(cascade = ALL)
    @JoinColumn(name = "SEQ_PROTOCOLO", referencedColumnName = "SEQ_PROTOCOLO", insertable = false, updatable = false)
    private Devolucao devolucao;
    
    @OneToMany(cascade = ALL)
    @JoinTable(name = "REMESSA_PREFERENCIA", schema = "RECEBIMENTO", joinColumns = @JoinColumn(name = "SEQ_PROTOCOLO", nullable = false),
		inverseJoinColumns = @JoinColumn(name = "SEQ_PREFERENCIA", nullable = false))
    private Set<Preferencia> preferencias = new HashSet<>(0);
    
    @Embedded
    @Column(nullable = false)
    private Recebedor recebedor;
    
    @Column(name = "DAT_RECEBIMENTO", nullable = false)
    private Date dataRecebimento = new Date();
    
    @Column(name = "TIP_SIGILO", nullable = false)
    @Enumerated(EnumType.STRING)
    private Sigilo sigilo;

    @OneToMany(cascade = ALL)
    @JoinTable(name = "REMESSA_EVENTO", schema = "RECEBIMENTO", joinColumns = @JoinColumn(name = "SEQ_PROTOCOLO", nullable = false),
		inverseJoinColumns = @JoinColumn(name = "SEQ_EVENTO", nullable = false))
    private Set<Evento> eventos = new TreeSet<>(comparing(Evento::criacao));
    
    public Remessa() {
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
	public Remessa(Protocolo protocolo, Integer volumes, Integer apensos, FormaRecebimento formaRecebimento,
			String numeroSedex, Sigilo sigilo, Recebedor recebedor, Status status) {
		Validate.notNull(protocolo, "Protocolo requerido.");
		Validate.inclusiveBetween(1, Integer.MAX_VALUE, volumes, "Volumes inválido.");
		Validate.inclusiveBetween(0, Integer.MAX_VALUE, apensos, "Apensos inválido.");
		Validate.notNull(formaRecebimento, "Forma de recebimento requerida.");
		Validate.isTrue(!formaRecebimento.exigeNumeracao() || !StringUtils.isEmpty(numeroSedex),
				"Forma de recebimento exige número de sedex.");
		Validate.notNull(sigilo, "Sigilo requerido.");
		Validate.notNull(recebedor, "Recebedor requerido.");
    	Validate.notNull(status, "Status requerido.");
    	
    	this.protocoloId = protocolo.identity();
    	this.numero = protocolo.numero();
        this.volumes = volumes;
        this.apensos = apensos;
        this.formaRecebimento = formaRecebimento;
        this.numeroSedex = numeroSedex;
        this.sigilo = sigilo;
        this.recebedor = recebedor;
        this.status = status;
        
        registrarEvento(new RemessaRegistrada(protocolo.identity().toLong(), protocolo.toString()));
    }
    
	private void registrarEvento(DomainEvent<?> evento) {
		eventos.add(new Evento(evento));
	}
	
    /**
     * @return
     */
    public abstract TipoProcesso tipoProcesso();
    
    protected void preautuar(ClassePeticionavel classe, Set<Preferencia> preferencias, Sigilo sigilo, Status status) {
		Validate.notNull(classe, "Classe requerida.");
		Validate.notNull(sigilo, "Sigilo requerido.");
		Validate.notNull(status, "Status requerido.");
    	Validate.isTrue(tipoProcesso().equals(classe.tipo()), "O tipo da remessa e da classe são incompatíveis.");
		Validate.isTrue(!Optional.ofNullable(preferencias).isPresent() || classe.preferencias().containsAll(preferencias),
				"Alguma(s) preferência(s) não pertence(m) à classe selecionada.");
    	
    	this.classe = classe;
    	this.sigilo = sigilo;
    	this.preferencias = Optional.ofNullable(preferencias).orElse(new HashSet<>(0));
    	this.status = status;
    	
    	registrarEvento(new RecebimentoFinalizado(identity().toLong(), classe.identity().toString(), tipoProcesso().toString(), sigilo().toString(), isCriminalEleitoral()));
    }
    
    /**
     * @param motivacao
     * @param status
     */
    public void iniciarDevolucao(String motivacao, Status status) {
    	Validate.notNull(status, "Status requerido.");
    	
        devolucao = new Devolucao(protocoloId, motivacao);
        this.status = status;
    }

    /**
     * @param motivo
     * @param modelo
     * @param texto
     * @param status
     */
    public void elaborarDevolucao(MotivoDevolucao motivo, ModeloDevolucao modelo, TextoId texto, Status status) {
    	Validate.notNull(devolucao, "O processo de devolução não está iniciado.");
    	Validate.notNull(status, "Status requerido.");
    	
    	devolucao = new Devolucao(protocoloId, devolucao.motivacao(), motivo, modelo, texto);
        this.status = status;
    }

    /**
     * @param status
     */
    public void devolver(Status status) {
    	Validate.notNull(status, "Status requerido.");
    	
        this.status = status;
    }
    
    /**
     * @return
     */
    public Numero numero() {
    	return numero;
    }
    
    /**
     * @return
     */
    public Sigilo sigilo() {
    	return sigilo;
    }

    @Override
    public ProtocoloId identity() {
        return protocoloId;
    }

	/**
	 * @return
	 */
	public ClassePeticionavel classe() {
		return classe;
	}

	/**
	 * @return
	 */
	public Integer volumes() {
		return volumes;
	}

	/**
	 * @return
	 */
	public Integer apensos() {
		return apensos;
	}

	/**
	 * @return
	 */
	public FormaRecebimento formaRecebimento() {
		return formaRecebimento;
	}

	/**
	 * @return
	 */
	public String numeroSedex() {
		return numeroSedex;
	}

	/**
	 * @return
	 */
	public Devolucao devolucao() {
		return devolucao;
	}
	
	public Boolean isCriminalEleitoral() {
		return preferencias.stream().anyMatch(preferencia -> preferencia.isCriminalEleitoral());
	}
    
}
