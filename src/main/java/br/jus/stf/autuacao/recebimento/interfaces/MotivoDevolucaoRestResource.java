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
import br.jus.stf.autuacao.recebimento.interfaces.dto.MotivoDevolucaoDto;
import br.jus.stf.autuacao.recebimento.interfaces.dto.MotivoDevolucaoDtoAssembler;

/**
 * API REST usada para recuperar os motivos de devolução.
 * 
 * @author Anderson.Araujo
 * @since 15.03.2016
 */
@RestController
@RequestMapping("/api/remessas/motivos-devolucao")
public class MotivoDevolucaoRestResource {

    @Autowired
    private RemessaRepository remessaRepository;

    @Autowired
    private MotivoDevolucaoDtoAssembler motivoDevolucaoDtoAssembler;

    @Autowired
    private ModeloDevolucaoRepository modeloDevolucaoRepository;

    @Autowired
    private ModeloDtoAssembler modeloDtoAssembler;

    /**
     * @return Todos os motivos de devolução de remessa.
     */
    @ApiOperation(value = "Lista todos os motivos de devolução de remessa.")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<MotivoDevolucaoDto> listar() {
        return remessaRepository.findAllMotivoDevolucao().stream()
                .map(motivoDevolucaoDtoAssembler::toDto)
                .collect(Collectors.toList());
    }

    /**
     * @param idMotivo Identificador do motivo de devolução.
     * @return Todos os modelos de devolução de um motivo.
     */
    @ApiOperation(value = "Lista todos os modelos de devolução de um motivo.")
    @RequestMapping(value = "/{idMotivo}/modelos", method = RequestMethod.GET)
    public List<ModeloDevolucaoDto> consultarPorModeloDevolucao(@PathVariable("idMotivo") Long idMotivo) {
        MotivoDevolucao motivo = remessaRepository.findOneMotivoDevolucao(idMotivo);
        List<ModeloDevolucao> modelos = modeloDevolucaoRepository.findModeloDevolucaoByMotivoDevolucao(motivo);

        return modelos.stream()
                .map(modeloDtoAssembler::toDto)
                .collect(Collectors.toList());
    }
}