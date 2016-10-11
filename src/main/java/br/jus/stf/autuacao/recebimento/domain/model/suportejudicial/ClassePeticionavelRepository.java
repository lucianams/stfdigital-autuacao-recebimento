package br.jus.stf.autuacao.recebimento.domain.model.suportejudicial;

import java.util.List;

import br.jus.stf.core.shared.classe.ClasseId;
import br.jus.stf.core.shared.processo.TipoProcesso;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 06.04.2016
 */
public interface ClassePeticionavelRepository {

    /**
     * @return Todas as classes peticionáveis.
     */
    List<ClassePeticionavel> findAll();

    /**
     * @param id Identificador da classe procurada.
     * @return Uma classe peticionável.
     */
    ClassePeticionavel findOne(ClasseId id);

    /**
     * @param tipo Tipo de processo associado as classes procuradas.
     * @return Todas as classes do tipo de processo.
     */
    List<ClassePeticionavel> findByTipo(TipoProcesso tipo);

}
