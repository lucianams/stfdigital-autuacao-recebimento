package br.jus.stf.autuacao.recebimento.interfaces;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.jus.stf.autuacao.recebimento.application.RecebimentoApplicationService;
import br.jus.stf.autuacao.recebimento.application.commands.AssinarOficioParaDevolucaoCommand;
import br.jus.stf.autuacao.recebimento.application.commands.DevolverRemessaCommand;
import br.jus.stf.autuacao.recebimento.application.commands.PreautuarOriginarioCommand;
import br.jus.stf.autuacao.recebimento.application.commands.PreautuarRecursalCommand;
import br.jus.stf.autuacao.recebimento.application.commands.PrepararOficioParaDevolucaoCommand;
import br.jus.stf.autuacao.recebimento.application.commands.RegistrarRemessaCommand;
import br.jus.stf.autuacao.recebimento.domain.model.FormaRecebimento;
import br.jus.stf.autuacao.recebimento.domain.model.RemessaRepository;
import br.jus.stf.autuacao.recebimento.domain.model.suportejudicial.ClassePeticionavelRepository;
import br.jus.stf.autuacao.recebimento.interfaces.dto.ClasseDto;
import br.jus.stf.autuacao.recebimento.interfaces.dto.ClasseDtoAssembler;
import br.jus.stf.autuacao.recebimento.interfaces.dto.DevolucaoDto;
import br.jus.stf.autuacao.recebimento.interfaces.dto.DevolucaoDtoAssembler;
import br.jus.stf.autuacao.recebimento.interfaces.dto.FormaRecebimentoDto;
import br.jus.stf.autuacao.recebimento.interfaces.dto.RemessaDto;
import br.jus.stf.autuacao.recebimento.interfaces.dto.RemessaDtoAssembler;
import br.jus.stf.autuacao.recebimento.interfaces.dto.SigiloDto;
import br.jus.stf.core.shared.processo.Sigilo;
import br.jus.stf.core.shared.processo.TipoProcesso;
import br.jus.stf.core.shared.protocolo.ProtocoloId;

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
	
	@Autowired 
	private RemessaRepository remessaRepository;
	
	@Autowired
	private RemessaDtoAssembler remessaDtoAssembler;
	
	@Autowired
	private ClassePeticionavelRepository classePeticionavelRepository;
	
	@Autowired
	private ClasseDtoAssembler classeDtoAssembler;
	
	@Autowired
	private DevolucaoDtoAssembler devolucaoDtoAssembler;
    
	/**
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<RemessaDto> listar() {
		return remessaRepository.findAll().stream().map(remessaDtoAssembler::toDto).collect(Collectors.toList());
	}
	
    /**
     * @param command
     * @param binding
     */
    @RequestMapping(value = "/recebimento", method = RequestMethod.POST)
    public void registrar(@RequestBody @Valid RegistrarRemessaCommand command, BindingResult binding) {
        if (binding.hasErrors()) {
            throw new IllegalArgumentException(message(binding));
        }
        
        recebimentoApplicationService.handle(command);
    }
    
    /**
     * @param id
     * @return
     */
    @RequestMapping(value="/{protocoloId}", method = RequestMethod.GET)
    public RemessaDto consultarRemessa(@PathVariable("protocoloId") Long id){
    	return  remessaDtoAssembler.toDto(remessaRepository.findOne(new ProtocoloId(id)));
    }
    

    /**
     * @param command
     * @param binding
     */
    @RequestMapping(value = "/preautuacao", method = RequestMethod.POST)
    public void preautuar(@RequestBody @Valid PreautuarOriginarioCommand command, BindingResult binding) {
        if (binding.hasErrors()) {
            throw new IllegalArgumentException(message(binding));
        }
        
        recebimentoApplicationService.handle(command);
    }
    
    /**
     * @param command
     * @param binding
     */
    @RequestMapping(value = "/preautuacao-recursal", method = RequestMethod.POST)
    public void preautuarRecursal(@RequestBody @Valid PreautuarRecursalCommand command, BindingResult binding) {
        if (binding.hasErrors()) {
            throw new IllegalArgumentException(message(binding));
        }
        
        recebimentoApplicationService.handle(command);
    }
    
    /**
     * @param command
     * @param binding
     */
    @RequestMapping(value = "/devolucao", method = RequestMethod.POST)
    public void devolver(@RequestBody @Valid DevolverRemessaCommand command, BindingResult binding) {
        if (binding.hasErrors()) {
            throw new IllegalArgumentException(message(binding));
        }
        
        recebimentoApplicationService.handle(command);
    }

    /**
     * @param command
     * @param binding
     */
    @RequestMapping(value = "/devolucao-oficio", method = RequestMethod.POST)
    public void prepararOficio(@RequestBody @Valid PrepararOficioParaDevolucaoCommand command, BindingResult binding) {
        if (binding.hasErrors()) {
            throw new IllegalArgumentException(message(binding));
        }
        
        recebimentoApplicationService.handle(command);
    }

    /**
     * @param command
     * @param binding
     */
    @RequestMapping(value = "/devolucao-assinatura", method = RequestMethod.POST)
    public void assinarOficio(@RequestBody @Valid AssinarOficioParaDevolucaoCommand command, BindingResult binding) {
        if (binding.hasErrors()) {
            throw new IllegalArgumentException(message(binding));
        }
        
        recebimentoApplicationService.handle(command);
    }
	
	/**
	 * @return
	 */
	@RequestMapping(value="/formas-recebimento", method = RequestMethod.GET)
    public List<FormaRecebimentoDto> consultarFormasRecebimento(){
    	return Arrays.asList(FormaRecebimento.values()).stream()
    			.map(forma -> new FormaRecebimentoDto(forma.name(), forma.descricao(), forma.exigeNumeracao()))
    			.collect(Collectors.toList());
    }
	
	/**
	 * @return
	 */
	@RequestMapping(value="/sigilos", method = RequestMethod.GET)
    public List<SigiloDto> consultarSigilos(){
    	return Arrays.asList(Sigilo.values()).stream().map(sigilo -> new SigiloDto(sigilo.toString(), sigilo.descricao())).collect(Collectors.toList());
    }
	
	
	/**
	 * @return
	 */
	@RequestMapping(value="/classes", method = RequestMethod.GET)
    public List<ClasseDto> listarClasses(){
		return classePeticionavelRepository.findAll().stream().map(classeDtoAssembler::toDto)
				.collect(Collectors.toList());
    }
	
	/**
	 * @param tipoRemessa
	 * @return
	 */
	@RequestMapping(value="/classes/tipos-remessa/{tipoRemessa}", method = RequestMethod.GET)
    public List<ClasseDto> consultarClassesPorTipoRemessa(@PathVariable("tipoRemessa") String tipoRemessa){
    	TipoProcesso tipoProcesso = TipoProcesso.valueOf(tipoRemessa); 
		
    	return classePeticionavelRepository.findByTipo(tipoProcesso).stream().map(classeDtoAssembler::toDto)
				.collect(Collectors.toList());
    }

    /**
     * @param id
     * @return
     */
    @RequestMapping(value="/{protocoloId}/devolucao", method = RequestMethod.GET)
    public DevolucaoDto consultarDevolucao(@PathVariable("protocoloId") Long id) {
    	return devolucaoDtoAssembler.toDto(remessaRepository.findOne(new ProtocoloId(id)));
    }
	
	private String message(BindingResult binding) {
		return String.format(REMESSA_INVALIDA_PATTERN, binding.getAllErrors());
	}

}
