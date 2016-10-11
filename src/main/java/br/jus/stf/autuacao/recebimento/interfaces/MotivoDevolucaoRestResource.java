package br.jus.stf.autuacao.recebimento.interfaces;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.ApiOperation;

import br.jus.stf.autuacao.recebimento.domain.model.RemessaRepository;
import br.jus.stf.autuacao.recebimento.interfaces.dto.MotivoDevolucaoDto;
import br.jus.stf.autuacao.recebimento.interfaces.dto.MotivoDevolucaoDtoAssembler;

/**
 * API REST usada para recuperar os motivos de devolução de uma petição.
 * 
 * @author Anderson.Araujo
 * @since 15.03.2016
 */
@RestController
@RequestMapping("/api/devolucao/motivos-devolucao")
public class MotivoDevolucaoRestResource {

    @Autowired
    private RemessaRepository remessaRepository;

    @Autowired
    private MotivoDevolucaoDtoAssembler motivoDevolucaoDtoAssembler;

    /**
     * @return Todos os motivos de devolução de remessa.
     */
    @ApiOperation(value = "Lista todos os motivos de devolução de remessa.", httpMethod = "GET")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<MotivoDevolucaoDto> listar() {
        return remessaRepository.findAllMotivoDevolucao().stream()
                .map(motivoDevolucaoDtoAssembler::toDto)
                .collect(Collectors.toList());
    }
}