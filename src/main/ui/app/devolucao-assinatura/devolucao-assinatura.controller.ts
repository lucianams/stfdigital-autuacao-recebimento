import IStateService = angular.ui.IStateService;
import {DevolucaoAssinaturaService, AssinarOficioParaDevolucaoCommand, Devolucao} from "./devolucao-assinatura.service";
import devolucaoAssinatura from "./devolucao-assinatura.module";

class DevolucaoEmAssinatura extends Devolucao {
    public progressTracker: app.certification.ProgressTracker;
}

export class DevolucaoAssinaturaController {
	
    static $inject = ['$state', 'app.recebimento.devolucao-assinatura.DevolucaoAssinaturaService', 'devolucoes', 'app.certification.SignatureService',
        'app.support.messaging.MessagesService'];

    public devolucoesParaAssinar: DevolucaoEmAssinatura[] = [];

    constructor(private $state: IStateService, private devolucaoAssinaturaService: DevolucaoAssinaturaService,
                public devolucoes: Devolucao[], private signatureService: app.certification.SignatureService,
                private messagesService: app.support.messaging.MessagesService) {
    	console.log(devolucoes);
    }
    
    public urlConteudo(devolucao: Devolucao): string {
    	return this.devolucaoAssinaturaService.montarUrlConteudoTexto(devolucao.textoId);
    }
    
    public isIndeterminate(): boolean {
        return this.devolucoesParaAssinar.length != 0 && this.devolucoesParaAssinar.length != this.devolucoes.length;
    }

    public isChecked(): boolean {
        return this.devolucoesParaAssinar.length == this.devolucoes.length;
    }

    public toggleAll(): void {
        if (this.devolucoesParaAssinar.length == this.devolucoes.length) {
            this.devolucoesParaAssinar = [];
        } else if (this.devolucoesParaAssinar.length >= 0) {
            this.devolucoesParaAssinar = <DevolucaoEmAssinatura[]>this.devolucoes.slice(0);
        }
    }

    public assinar(): void {
        if (this.devolucoesParaAssinar.length == 0) {
            this.messagesService.error('É necessário selecionar pelo menos uma remessa para assinar.')
        }
        console.log(this.devolucoesParaAssinar);
        let signingManager: app.certification.SigningManager = this.signatureService.signingManager();
        for (let devolucao of this.devolucoesParaAssinar) {
            let signer: app.certification.Signer = signingManager.createSigner();
            let documentoId: number = 5;
            devolucao.progressTracker = signer.getProgressTracker();
            signer.onSignerReady((signerDto: app.certification.SignerDto) => {
                
            });
            signer.onSigningCompleted(() => {

            });
            signer.onErrorCallback((signingError: any) => {

            });
            signer.start();
        }
    }

}

devolucaoAssinatura.controller('app.recebimento.devolucao-assinatura.DevolucaoAssinaturaController', DevolucaoAssinaturaController);
export default devolucaoAssinatura;