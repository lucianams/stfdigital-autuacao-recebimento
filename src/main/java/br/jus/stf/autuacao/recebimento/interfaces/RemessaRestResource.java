package br.jus.stf.autuacao.recebimento.interfaces;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.jus.stf.autuacao.recebimento.application.RecebimentoApplicationService;
import br.jus.stf.autuacao.recebimento.application.commands.AssinarOficioParaDevolucaoCommand;
import br.jus.stf.autuacao.recebimento.application.commands.PreautuarRemessaCommand;
import br.jus.stf.autuacao.recebimento.application.commands.PrepararOficioParaDevolucaoCommand;
import br.jus.stf.autuacao.recebimento.application.commands.RegistrarRemessaCommand;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 18.12.2015
 */
@RestController
@RequestMapping("/api/remessas")
public class RemessaRestResource {
    
    private static final String REMESSA_INVALIDA_PATTERN = "Remessa Inválida: %S";
	@Autowired
    private RecebimentoApplicationService recebimentoApplicationService; 
    
    @RequestMapping(method = RequestMethod.POST)
    public void registrar(@RequestBody @Valid RegistrarRemessaCommand command, BindingResult binding) {
        if (binding.hasErrors()) {
            throw new IllegalArgumentException(message(binding));
        }
        
        recebimentoApplicationService.handle(command);
    }

    @RequestMapping(value = "/preautuacao", method = RequestMethod.POST)
    public void preautuar(@RequestBody @Valid PreautuarRemessaCommand command, BindingResult binding) {
        if (binding.hasErrors()) {
            throw new IllegalArgumentException(message(binding));
        }
        
        recebimentoApplicationService.handle(command);
    }

    @RequestMapping(value = "/devolucao", method = RequestMethod.POST)
    public void devolver(@RequestBody @Valid PrepararOficioParaDevolucaoCommand command, BindingResult binding) {
        if (binding.hasErrors()) {
            throw new IllegalArgumentException(message(binding));
        }
        
        recebimentoApplicationService.handle(command);
    }

    @RequestMapping(value = "/assinatura", method = RequestMethod.POST)
    public void assinar(@RequestBody @Valid AssinarOficioParaDevolucaoCommand command, BindingResult binding) {
        if (binding.hasErrors()) {
            throw new IllegalArgumentException(message(binding));
        }
        
        recebimentoApplicationService.handle(command);
    }

	private String message(BindingResult binding) {
		return String.format(REMESSA_INVALIDA_PATTERN, binding.getAllErrors());
	}

}
