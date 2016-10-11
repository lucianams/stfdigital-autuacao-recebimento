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
public abstract class Remessa extends EntitySupport<Remessa, ProtocoloId>
        implements AggregateRoot<Remessa, ProtocoloId> {

    private static final String STATUS_REQUERIDO = "Status requerido.";

    @EmbeddedId
    private ProtocoloId protocoloId;

    @ManyToOne
    @JoinColumn(name = "SIG_CLASSE", nullable = false)
    private ClassePeticionavel classe;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "numero", column = @Column(name = "NUM_REMESSA", nullable = false)),
            @AttributeOverride(name = "ano", column = @Column(name = "NUM_ANO", nullable = false))
    })
    private Numero numero;

    @Column(name = "QTD_VOLUME", nullable = false)
    private Integer volumes;

    @Column(name = "QTD_APENSO")
    private Integer apensos;

    @Embedded
    private TipoRecebimento tipoRecebimento;

    @Column(name = "TIP_STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "SEQ_PROTOCOLO", referencedColumnName = "SEQ_PROTOCOLO", insertable = false, updatable = false)
    private Devolucao devolucao;

    @OneToMany(cascade = ALL)
    @JoinTable(name = "REMESSA_PREFERENCIA", schema = "RECEBIMENTO",
            joinColumns = @JoinColumn(name = "SEQ_PROTOCOLO", nullable = false),
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
    @JoinTable(name = "REMESSA_EVENTO", schema = "RECEBIMENTO",
            joinColumns = @JoinColumn(name = "SEQ_PROTOCOLO", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "SEQ_EVENTO", nullable = false))
    private Set<Evento> eventos = new TreeSet<>(comparing(Evento::criacao));

    Remessa() {
        // Construtor default deve ser utilizado apenas pelo Hibernate.
    }

    /**
     * @param protocolo Protocolo com identificação da remessa.
     * @param volumes Quantidade de volumes da remessa.
     * @param apensos Quantidade de apensos da remessa.
     * @param tipoRecebimento Dados do tipo de recebimento da remessa.
     * @param sigilo Grau de sigilo da remessa.
     * @param recebedor Usuário que registrou a remessa.
     * @param status Status inicial do BPM para remessa.
     */
    public Remessa(Protocolo protocolo, Integer volumes, Integer apensos, TipoRecebimento tipoRecebimento,
            Sigilo sigilo, Recebedor recebedor, Status status) {
        Validate.notNull(protocolo, "Protocolo requerido.");
        Validate.inclusiveBetween(1, Integer.MAX_VALUE, volumes, "Quantidade de volume deve ser maior ou igual a 1.");
        Validate.inclusiveBetween(0, Integer.MAX_VALUE, apensos, "Quantidade de apenso deve ser maior ou igual a 0.");
        Validate.notNull(tipoRecebimento, "Tipo de recebimento requerido.");
        Validate.notNull(sigilo, "Sigilo requerido.");
        Validate.notNull(recebedor, "Recebedor requerido.");
        Validate.notNull(status, STATUS_REQUERIDO);

        this.protocoloId = protocolo.identity();
        this.numero = protocolo.numero();
        this.volumes = volumes;
        this.apensos = apensos;
        this.tipoRecebimento = tipoRecebimento;
        this.sigilo = sigilo;
        this.recebedor = recebedor;
        this.status = status;

        registrarEvento(new RemessaRegistrada(protocolo.identity().toLong(), protocolo.toString()));
    }

    private void registrarEvento(DomainEvent<?> evento) {
        eventos.add(new Evento(evento));
    }

    /**
     * @param classe Classe da remessa.
     * @param preferencias Subconjunto de preferências da classe que estão associadas com a remessa.
     * @param sigilo Revisão do grau de sigilo da remessa.
     * @param status Status do BPM ligado com a fase de preautuação.
     */
    protected void preautuar(ClassePeticionavel classe, Set<Preferencia> preferencias, Sigilo sigilo, Status status) {
        Validate.notNull(classe, "Classe requerida.");
        Validate.notNull(sigilo, "Sigilo requerido.");
        Validate.notNull(status, STATUS_REQUERIDO);
        Validate.isTrue(isTipoProcessoRemessaEClasseCompativeis(classe),
                "O tipo da remessa e da classe são incompatíveis.");
        Validate.isTrue(
                isPreferenciasRemessaEClasseCompativeis(classe, preferencias),
                "Alguma(s) preferência(s) não pertence(m) à classe selecionada.");

        this.classe = classe;
        this.sigilo = sigilo;
        this.preferencias = Optional.ofNullable(preferencias).orElse(new HashSet<>(0));
        this.status = status;

        registrarEvento(new RecebimentoFinalizado(identity().toLong(), classe.identity().toString(),
                tipoProcesso().toString(), sigilo().toString(), isCriminalEleitoral()));
    }

    private static boolean isPreferenciasRemessaEClasseCompativeis(ClassePeticionavel classe,
            Set<Preferencia> preferencias) {
        return !Optional.ofNullable(preferencias).isPresent() || classe.preferencias().containsAll(preferencias);
    }

    private boolean isTipoProcessoRemessaEClasseCompativeis(ClassePeticionavel classe) {
        return tipoProcesso().equals(classe.tipo());
    }

    /**
     * @param motivacao Motivação da devolução da remessa.
     * @param status Status do BPM para início de devolução.
     */
    public void iniciarDevolucao(String motivacao, Status status) {
        Validate.notNull(status, STATUS_REQUERIDO);

        devolucao = new Devolucao(protocoloId, motivacao);
        this.status = status;
    }

    /**
     * @param motivo Motivo de devolução.
     * @param modelo Modelo do ofício de devolução.
     * @param texto Texto associado com a devolução.
     * @param status Status do BPM para elaboração do ofício.
     */
    public void elaborarDevolucao(MotivoDevolucao motivo, ModeloDevolucao modelo, TextoId texto, Status status) {
        Validate.notNull(devolucao, "O processo de devolução não está iniciado.");
        Validate.notNull(status, STATUS_REQUERIDO);

        devolucao = new Devolucao(protocoloId, devolucao.motivacao(), motivo, modelo, texto);
        this.status = status;
    }

    /**
     * @param status Status do BPM para remessa devolvida.
     */
    public void devolver(Status status) {
        Validate.notNull(status, STATUS_REQUERIDO);

        this.status = status;
    }

    /**
     * @return Número da remssa.
     */
    public Numero numero() {
        return numero;
    }

    /**
     * @return Grau de sigilo da remessa.
     */
    public Sigilo sigilo() {
        return sigilo;
    }

    /**
     * @return Classe da remessa preautuada.
     */
    public ClassePeticionavel classe() {
        return classe;
    }

    /**
     * @return Quantidade de volumes.
     */
    public Integer volumes() {
        return volumes;
    }

    /**
     * @return Quantidade de apensos.
     */
    public Integer apensos() {
        return apensos;
    }

    /**
     * @return Dados do tipo de recebimento.
     */
    public TipoRecebimento tipoRecebimento() {
        return tipoRecebimento;
    }

    /**
     * @return Devolução associada com a remessa.
     */
    public Devolucao devolucao() {
        return devolucao;
    }

    public Boolean isCriminalEleitoral() {
        return preferencias.stream()
                .anyMatch(preferencia -> preferencia.isCriminalEleitoral());
    }

    @Override
    public ProtocoloId identity() {
        return protocoloId;
    }

    /**
     * @return Tipo de processo da remessa.
     */
    public abstract TipoProcesso tipoProcesso();

}
