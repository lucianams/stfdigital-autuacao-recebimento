package br.jus.stf.autuacao.recebimento.interfaces;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.ApiOperation;

import br.jus.stf.autuacao.recebimento.application.RecebimentoApplicationService;
import br.jus.stf.autuacao.recebimento.application.commands.AssinarOficioParaDevolucaoCommand;
import br.jus.stf.autuacao.recebimento.application.commands.DevolverRemessaCommand;
import br.jus.stf.autuacao.recebimento.application.commands.PreautuarOriginarioCommand;
import br.jus.stf.autuacao.recebimento.application.commands.PreautuarRecursalCommand;
import br.jus.stf.autuacao.recebimento.application.commands.PrepararOficioParaDevolucaoCommand;
import br.jus.stf.autuacao.recebimento.application.commands.RegistrarRemessaCommand;
import br.jus.stf.autuacao.recebimento.domain.model.RemessaRepository;
import br.jus.stf.autuacao.recebimento.interfaces.dto.DevolucaoDto;
import br.jus.stf.autuacao.recebimento.interfaces.dto.DevolucaoDtoAssembler;
import br.jus.stf.autuacao.recebimento.interfaces.dto.RemessaDto;
import br.jus.stf.autuacao.recebimento.interfaces.dto.RemessaDtoAssembler;
import br.jus.stf.core.shared.protocolo.ProtocoloId;

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
public class RemessaRestResource {

    private static final String REMESSA_INVALIDA_PATTERN = "Remessa Inválida: %S";

    @Autowired
    private RecebimentoApplicationService recebimentoApplicationService;

    @Autowired
    private RemessaRepository remessaRepository;

    @Autowired
    private RemessaDtoAssembler remessaDtoAssembler;

    @Autowired
    private DevolucaoDtoAssembler devolucaoDtoAssembler;

    /**
     * @return Todas as remessas.
     */
    @ApiOperation(value = "Lista todas as remessas.")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<RemessaDto> listar() {
        return remessaRepository.findAll().stream()
                .map(remessaDtoAssembler::toDto)
                .collect(Collectors.toList());
    }

    /**
     * @param command Command para registrar uma remessa.
     * @param binding Binding com resultado da validação.
     */
    @ApiOperation(value = "Registra uma remessa.")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void registrar(@RequestBody @Valid RegistrarRemessaCommand command, BindingResult binding) {
        isValid(binding);
        recebimentoApplicationService.handle(command);
    }

    /**
     * @param id Identificador da remessa.
     * @return DTO da remessa procurada.
     */
    @ApiOperation(value = "Consulta uma remessa pelo ID.")
    @RequestMapping(value = "/{protocoloId}", method = RequestMethod.GET)
    public RemessaDto consultarRemessa(@PathVariable("protocoloId") Long protocoloId) {
        return remessaDtoAssembler.toDto(remessaRepository.findOne(new ProtocoloId(protocoloId)));
    }

    /**
     * @param id Identificador da remessa.
     * @param command Command para preautuar uma remessa originária.
     * @param binding Binding com resultado da validação.
     */
    @ApiOperation(value = "Preautua uma remessa originária.")
    @RequestMapping(value = "/{protocoloId}/preautuacao-originario", method = RequestMethod.PUT)
    public void preautuar(@PathVariable("protocoloId") Long protocoloId,
            @RequestBody @Valid PreautuarOriginarioCommand command,
            BindingResult binding) {
        isValid(protocoloId, command.getProtocoloId(), binding);
        recebimentoApplicationService.handle(command);
    }

    /**
     * @param id Identificador da remessa.
     * @param command Command para preautuar uma remessa recursal.
     * @param binding Binding com resultado da validação.
     */
    @ApiOperation(value = "Preautua uma remessa recursal.")
    @RequestMapping(value = "/{protocoloId}/preautuacao-recursal", method = RequestMethod.PUT)
    public void preautuarRecursal(@PathVariable("protocoloId") Long protocoloId,
            @RequestBody @Valid PreautuarRecursalCommand command, BindingResult binding) {
        isValid(protocoloId, command.getProtocoloId(), binding);
        recebimentoApplicationService.handle(command);
    }

    /**
     * @param id Identificador da remessa.
     * @param command Command para iniciar devolução de uma remessa.
     * @param binding Binding com resultado da validação.
     */
    @ApiOperation(value = "Inicia a devolução de uma remessa.")
    @RequestMapping(value = "/{protocoloId}/devolucao", method = RequestMethod.POST)
    public void devolver(@PathVariable Long protocoloId, @RequestBody @Valid DevolverRemessaCommand command,
            BindingResult binding) {
        isValid(protocoloId, command.getProtocoloId(), binding);
        recebimentoApplicationService.handle(command);
    }

    /**
     * @param command Command que prepara ofício de devolução.
     * @param binding Binding com resultado da validação.
     */
    @ApiOperation(value = "Prepara ofício de devolução de uma remessa.")
    @RequestMapping(value = "/{protocoloId}/preparacao-devolucao", method = RequestMethod.PUT)
    public void prepararDevolucao(@PathVariable Long protocoloId,
            @RequestBody @Valid PrepararOficioParaDevolucaoCommand command, BindingResult binding) {
        isValid(protocoloId, command.getProtocoloId(), binding);
        recebimentoApplicationService.handle(command);
    }

    /**
     * @param command Command para assinar ofício de devolução da remessa.
     * @param binding Binding com resultado da validação.
     */
    @ApiOperation(value = "Realiza a assinatura do ofício de devolução de uma remessa.")
    @RequestMapping(value = "/{protocoloId}/assinatura-devolucao", method = RequestMethod.PUT)
    public void assinarDevolucao(@PathVariable Long protocoloId,
            @RequestBody @Valid AssinarOficioParaDevolucaoCommand command,
            BindingResult binding) {
        isValid(protocoloId, command.getProtocoloId(), binding);
        recebimentoApplicationService.handle(command);
    }

    /**
     * @param id Identificador da remessa vinculada com a devolução.
     * @return DTO com devolução procurada.
     */
    @ApiOperation(value = "Retorna a devolução de uma remessa.")
    @RequestMapping(value = "/{protocoloId}/devolucoes", method = RequestMethod.GET)
    public DevolucaoDto consultarDevolucao(@PathVariable("protocoloId") Long id) {
        return devolucaoDtoAssembler.toDto(remessaRepository.findOne(new ProtocoloId(id)));
    }

    private static void isValid(Long protocoloIdPath, Long protocoloIdCommand, BindingResult binding) {
        isValid(binding);

        if (!protocoloIdPath.equals(protocoloIdCommand)) {
            throw new IllegalArgumentException(message(
                    Arrays.asList(
                            new ObjectError("Remessa", "Identificadores do comando incompatíveis."))));
        }
    }

    private static void isValid(BindingResult binding) {
        if (binding.hasErrors()) {
            throw new IllegalArgumentException(message(binding.getAllErrors()));
        }
    }

    private static String message(List<ObjectError> errors) {
        return String.format(REMESSA_INVALIDA_PATTERN, errors);
    }

}
