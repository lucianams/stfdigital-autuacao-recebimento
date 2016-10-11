package br.jus.stf.autuacao.recebimento.interfaces;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.ApiOperation;

import br.jus.stf.autuacao.recebimento.domain.model.MotivoDevolucao;
import br.jus.stf.autuacao.recebimento.domain.model.RemessaRepository;
import br.jus.stf.autuacao.recebimento.domain.model.documento.ModeloDevolucao;
import br.jus.stf.autuacao.recebimento.domain.model.documento.ModeloDevolucaoRepository;
import br.jus.stf.autuacao.recebimento.interfaces.dto.ModeloDevolucaoDto;
import br.jus.stf.autuacao.recebimento.interfaces.dto.ModeloDtoAssembler;

/**
 * API Rest para recuperar os modelos de devolução.
 * 
 * @author Tomas.Godoi
 * @since 31.05.2016
 */
@RestController
@RequestMapping("/api/devolucao")
public class ModeloDevolucaoRestResource {

    @Autowired
    private RemessaRepository remessaRepository;

    @Autowired
    private ModeloDevolucaoRepository modeloDevolucaoRepository;

    @Autowired
    private ModeloDtoAssembler modeloDtoAssembler;

    /**
     * @param idMotivo Identificador do motivo de devolução.
     * @return Todos os modelos de devolução de um motivo.
     */
    @ApiOperation(value = "Lista todos os modelos de devolução de um motivo.", httpMethod = "GET")
    @RequestMapping(value = "/motivos-devolucao/{idMotivo}/modelos", method = RequestMethod.GET)
    public List<ModeloDevolucaoDto> consultarPorModeloDevolucao(@PathVariable("idMotivo") Long idMotivo) {
        MotivoDevolucao motivo = remessaRepository.findOneMotivoDevolucao(idMotivo);
        List<ModeloDevolucao> modelos = modeloDevolucaoRepository.findModeloDevolucaoByMotivoDevolucao(motivo);

        return modelos.stream()
                .map(modeloDtoAssembler::toDto)
                .collect(Collectors.toList());
    }

}
