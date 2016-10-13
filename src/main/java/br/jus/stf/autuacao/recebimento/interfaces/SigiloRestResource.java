package br.jus.stf.autuacao.recebimento.interfaces;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.ApiOperation;

import br.jus.stf.autuacao.recebimento.interfaces.dto.SigiloDto;
import br.jus.stf.core.shared.processo.Sigilo;

/**
 * Serviços Rest de sigilo.
 * 
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 13.10.2016
 */
@RestController
@RequestMapping("/api/remessas")
public class SigiloRestResource {

    /**
     * @return Todos os sigilos.
     */
    @ApiOperation(value = "Lista todos os sigilos.", httpMethod = "GET")
    @RequestMapping(value = "/sigilos", method = RequestMethod.GET)
    public List<SigiloDto> consultarSigilos() {
        return Arrays.asList(Sigilo.values()).stream()
                .map(sigilo -> new SigiloDto(sigilo.toString(), sigilo.descricao()))
                .collect(Collectors.toList());
    }

}
