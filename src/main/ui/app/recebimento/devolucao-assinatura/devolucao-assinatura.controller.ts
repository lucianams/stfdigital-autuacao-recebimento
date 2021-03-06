import {AssinarOficioParaDevolucaoCommand, Devolucao, DevolucaoAssinaturaService, Documento} from
        "./devolucao-assinatura.service";
import devolucaoAssinatura from "./devolucao-assinatura.module";

export class DevolucaoEmAssinatura extends Devolucao {
    public progresso: () => number;
    public teminou: () => boolean;
}

export class DevolucaoAssinaturaController {

    public static $inject = ["$state", "app.recebimento.devolucao-assinatura.DevolucaoAssinaturaService", "devolucoes",
            "app.certification.SignatureService", "messagesService"];

    public path = {
        id: "tarefas.devolucao-assinatura",
        translation: "Assinatura do Ofício de Devolução",
        uisref: "app.tarefas.recebimento-devolucao-assinatura",
        parent: "tarefas"
    };

    public devolucoesParaAssinar: DevolucaoEmAssinatura[] = [];
    private devolucoesAssinadas: Devolucao[] = [];
    private devolucoesComErroDuranteAssinatura: Devolucao[] = [];

    public constructor(private $state: ng.ui.IStateService,
            private devolucaoAssinaturaService: DevolucaoAssinaturaService,
            public devolucoes: Devolucao[], private signatureService: app.certification.SignatureService,
            private messagesService: app.support.messaging.MessagesService) {
    }

    public urlConteudo(devolucao: Devolucao): string {
        return this.devolucaoAssinaturaService.montarUrlConteudoTexto(devolucao.textoId);
    }

    public isIndeterminate(): boolean {
        return this.devolucoesParaAssinar.length !== 0 && this.devolucoesParaAssinar.length !== this.devolucoes.length;
    }

    public isChecked(): boolean {
        return this.devolucoesParaAssinar.length === this.devolucoes.length;
    }

    public toggleAll(): void {
        if (this.devolucoesParaAssinar.length === this.devolucoes.length) {
            this.devolucoesParaAssinar = [];
        } else if (this.devolucoesParaAssinar.length >= 0) {
            this.devolucoesParaAssinar = <DevolucaoEmAssinatura[]>this.devolucoes.slice(0);
        }
    }

    public assinar(): void {
        if (this.devolucoesParaAssinar.length === 0) {
            this.messagesService.error("É necessário selecionar pelo menos uma remessa para assinar.");
            return;
        }
        let signingManager: app.certification.SigningManager = this.signatureService.signingManager(1);
        for (let devolucao of this.devolucoesParaAssinar) {
            let signer: app.certification.Signer = signingManager.createSigner();
            let lastStepFinished = false;
            devolucao.progresso = () => {
                if (lastStepFinished) {
                    return 100;
                } else {
                    return signer.getProgressTracker().currentProgressOfTotal(90);
                }
            };
            devolucao.teminou = () => {
                return lastStepFinished;
            };
            // Registra os callbacks de interação com o Signer
            signer.onSignerReady((signerDto: app.certification.SignerDto) => {
                this.devolucaoAssinaturaService.consultarDocumentoFinalDoTexto(devolucao.textoId)
                        .then((documento: Documento) => {
                    signer.provideExistingDocument(documento.documentoId);
                });
            });
            signer.onSigningCompleted(() => {
                signer.saveSignedDocument().then((savedSignedDocument: app.certification.SignedDocumentDto) => {
                    let command = new AssinarOficioParaDevolucaoCommand(devolucao.remessaProtocoloId,
                            savedSignedDocument.documentId);
                    this.devolucaoAssinaturaService.assinarOficioDevolucao(command).then(() => {
                        lastStepFinished = true;
                        this.devolucoesAssinadas.push(devolucao);
                        this.checarTerminoAssinatura();
                    }, () => {
                        this.messagesService.error("Erro ao finalizar a devolução da remessa.");
                    });
                });
            });
            signer.onErrorCallback((signingError) => {
                this.messagesService.error("Erro ao assinar documento de devolução da Remessa " +
                        devolucao.remessaNumero + "/" + devolucao.remessaAno + ". " + signingError.error);
                this.devolucoesComErroDuranteAssinatura.push(devolucao);
                this.checarTerminoAssinatura();
            });
            // Inicia a assinatura do item específico
            signer.start();
        }
    }

    private checarTerminoAssinatura() {
        if (this.devolucoesAssinadas.length > 0 &&
                (this.devolucoesAssinadas.length + this.devolucoesComErroDuranteAssinatura.length
                === this.devolucoesParaAssinar.length)) {
            this.completar();
        }
    }

    private completar() {
        this.$state.go("app.tarefas.minhas-tarefas");
        if (this.devolucoesAssinadas.length > 0) {
            this.messagesService.success(this.devolucoesAssinadas.length +
                    " documento(s) de devolução assinados com sucesso.");
        }
    }

}

devolucaoAssinatura.controller("app.recebimento.devolucao-assinatura.DevolucaoAssinaturaController",
        DevolucaoAssinaturaController);
export default devolucaoAssinatura;