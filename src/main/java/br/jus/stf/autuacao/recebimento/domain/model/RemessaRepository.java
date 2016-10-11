package br.jus.stf.autuacao.recebimento.domain.model;

import java.util.List;

import br.jus.stf.core.shared.protocolo.ProtocoloId;

/**
 * @author Rodrigo Barreiros
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 21.12.2015
 */
public interface RemessaRepository {

    /**
     * @param id Identificador da remessa procurada.
     * @return Uma remessa.
     */
    Remessa findOne(ProtocoloId id);

    /**
     * @param remessa Remesa que será salva.
     * @return Remessa salva.
     */
    <R extends Remessa> R save(R remessa);

    /**
     * @param id Identificador do motivo procurado.
     * @return Um motivo de devolução.
     */
    MotivoDevolucao findOneMotivoDevolucao(Long id);

    /**
     * @return Todos os motivos de devolução.
     */
    List<MotivoDevolucao> findAllMotivoDevolucao();

    /**
     * @param motivo Motivo que será salvo.
     * @return Motivo salvo.
     */
    <M extends MotivoDevolucao> M saveMotivoDevolucao(M motivo);

    /**
     * @param motivo Motivo que será excluído.
     */
    void deleteMotivoDevolucao(MotivoDevolucao motivo);

    /**
     * @return Todas as remessas.
     */
    List<Remessa> findAll();

}