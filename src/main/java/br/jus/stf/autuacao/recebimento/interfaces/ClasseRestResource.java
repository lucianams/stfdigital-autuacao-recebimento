package br.jus.stf.autuacao.recebimento.interfaces;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.ApiOperation;

import br.jus.stf.autuacao.recebimento.domain.model.suportejudicial.ClassePeticionavel;
import br.jus.stf.autuacao.recebimento.domain.model.suportejudicial.ClassePeticionavelRepository;
import br.jus.stf.autuacao.recebimento.interfaces.dto.ClasseDto;
import br.jus.stf.autuacao.recebimento.interfaces.dto.ClasseDtoAssembler;
import br.jus.stf.core.shared.processo.TipoProcesso;

/**
 * Serviço REST de classes peticionáveis.
 * 
 * @author anderson.araujo
 * @since 09.05.2016
 *
 */
@RestController
@RequestMapping("/api/classes")
public class ClasseRestResource {

    @Autowired
    private ClassePeticionavelRepository classePeticionavelRepository;

    @Autowired
    private ClasseDtoAssembler classeDtoAssembler;

    /**
     * @param tipoRemessa Tipo da remessa.
     * @return Lista todas as classes peticionáveis ou as de um tipo de remessa, quando informado.
     */
    @ApiOperation(value = "Lista todas as classes peticionáveis ou as de um tipo de remessa, quando informado.",
            httpMethod = "GET")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<ClasseDto> consultarClassesPorTipoRemessa(
            @RequestParam(name = "tipoRemessa", required = false) String tipoRemessa) {
        List<ClassePeticionavel> classes;

        if (StringUtils.isNotBlank(tipoRemessa)) {
            TipoProcesso tipoProcesso = TipoProcesso.valueOf(tipoRemessa);

            classes = classePeticionavelRepository.findByTipo(tipoProcesso);
        } else {
            classes = classePeticionavelRepository.findAll();
        }

        return classes.stream()
                .map(classeDtoAssembler::toDto)
                .collect(Collectors.toList());
    }
}
