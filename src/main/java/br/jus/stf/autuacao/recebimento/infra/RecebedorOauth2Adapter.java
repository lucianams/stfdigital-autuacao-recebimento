package br.jus.stf.autuacao.recebimento.infra;

import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import br.jus.stf.autuacao.recebimento.domain.RecebedorAdapter;
import br.jus.stf.autuacao.recebimento.domain.model.Recebedor;
import br.jus.stf.core.shared.identidade.PessoaId;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 05.07.2016
 */
@Component
public class RecebedorOauth2Adapter implements RecebedorAdapter {

    @Override
    @SuppressWarnings("unchecked")
    public Recebedor recebedor() {
        OAuth2Authentication authentication =
                (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();

        Map<String, Object> principal = (Map<String, Object>) authentication.getUserAuthentication().getDetails();

        Long pessoaId = Long.valueOf(principal.get("pessoaId").toString());

        String login = principal.get("login").toString();

        return new Recebedor(login, new PessoaId(pessoaId));
    }

}
