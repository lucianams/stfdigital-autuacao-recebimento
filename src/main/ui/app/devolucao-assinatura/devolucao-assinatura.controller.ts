import IStateService = angular.ui.IStateService;
import {DevolucaoAssinaturaService, AssinarOficioParaDevolucaoCommand, Devolucao, Documento} from "./devolucao-assinatura.service";
import devolucaoAssinatura from "./devolucao-assinatura.module";

export class DevolucaoEmAssinatura extends Devolucao {
    public calcularProgresso: () => number;
    public teminou: () => boolean;
}

export class DevolucaoAssinaturaController {
	
    static $inject = ['$state', 'app.recebimento.devolucao-assinatura.DevolucaoAssinaturaService', 'devolucoes', 'app.certification.SignatureService',
        'app.support.messaging.MessagesService'];

    public devolucoesParaAssinar: DevolucaoEmAssinatura[] = [];
    private devolucoesAssinadas: Devolucao[] = [];
    private devolucoesComErroDuranteAssinatura: Devolucao[] = [];

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
            let lastStepFinished = false;
            devolucao.calcularProgresso = () => {
                if (lastStepFinished) {
                    return 100;
                } else {
                    return signer.getProgressTracker().currentProgressOfTotal(90);
                }
            };
            devolucao.teminou = () => {
                return lastStepFinished;
            };
            signer.onSignerReady((signerDto: app.certification.SignerDto) => {
                this.devolucaoAssinaturaService.consultarDocumentoFinalDoTexto(devolucao.textoId).then((documento: Documento) => {
                    signer.provideExistingDocument(documento.documentoId);
                });
            });
            signer.onSigningCompleted(() => {
                console.log('onSigningCompleted');
                signer.saveSignedDocument().then((savedSignedDocument: app.certification.SignedDocumentDto) => {
                    let command = new AssinarOficioParaDevolucaoCommand(devolucao.remessaProtocoloId, savedSignedDocument.documentId);
                    this.devolucaoAssinaturaService.assinarOficioDevolucao(command).then(() => {
                        console.log('done');
                    });
                });
            });
            signer.onErrorCallback((signingError: any) => {
                this.messagesService.error('Erro ao assinar documento de devolução da Remessa ' + devolucao.remessaNumero + '/' + devolucao.remessaAno + '. ' + signingError);
                this.devolucoesComErroDuranteAssinatura.push(devolucao);
                this.checarTerminoAssinatura();
            });
            signer.start();
        }
    }

    private checarTerminoAssinatura() {
        if (this.devolucoesAssinadas.length + this.devolucoesComErroDuranteAssinatura.length == this.devolucoesParaAssinar.length) {
            this.completar();
        }
    }

    private completar() {
        this.$state.go('app.tarefas.minhas-tarefas', {}, { reload: true });
        if (this.devolucoesAssinadas.length > 0) {
            this.messagesService.success(this.devolucoesAssinadas.length + ' documento(s) de devolução assinados com sucesso.');
        }
    }

}

devolucaoAssinatura.controller('app.recebimento.devolucao-assinatura.DevolucaoAssinaturaController', DevolucaoAssinaturaController);
export default devolucaoAssinatura;