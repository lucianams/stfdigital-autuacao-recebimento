package br.jus.stf.autuacao.recebimento.interfaces;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.ApiOperation;

import br.jus.stf.autuacao.recebimento.domain.model.FormaRecebimento;
import br.jus.stf.autuacao.recebimento.interfaces.dto.FormaRecebimentoDto;

/**
 * Serviços Rest de Remessas.
 * 
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 18.12.2015
 */
@RestController
@RequestMapping("/api/remessas")
public class FormaRecebimentoRestResource {

    /**
     * @return Todas as formas de recebimento.
     */
    @ApiOperation(value = "Lista todas as formas de recebimento.")
    @RequestMapping(value = "/formas-recebimento", method = RequestMethod.GET)
    public List<FormaRecebimentoDto> consultarFormasRecebimento() {
        return Arrays.asList(FormaRecebimento.values()).stream()
                .map(forma -> new FormaRecebimentoDto(forma.name(), forma.descricao(), forma.exigeNumeracao()))
                .collect(Collectors.toList());
    }

}
