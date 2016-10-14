import IPromise = angular.IPromise;
import IStateService = angular.ui.IStateService;

import {Sigilo, TipoProcesso} from "../services/model";
import {PeticaoFisicaCommand, PeticaoFisicaService} from "./peticao-fisica.service";
import recebimento from "./peticao-fisica.module";

export class PeticaoFisicaController {

    public cmd: PeticaoFisicaCommand = new PeticaoFisicaCommand();
    public path = {
        id: "novo-processo.recebimento",
        translation: "Recebimento",
        uisref: "app.novo-processo.recebimento-peticao-fisica",
        parent: "novo-processo"
    };

    public static $inject = ["$state", "app.recebimento.peticoes-fisicas.PeticaoFisicaService", "formasRecebimento",
            "messagesService", "sigilos", "tiposProcessos"];

    public constructor(private $state: IStateService,
                private peticaoFisicaService: PeticaoFisicaService,
                public formasRecebimento,
                private messagesService: app.support.messaging.MessagesService,
                public sigilos: Sigilo[],
                public tiposProcessos: Array<TipoProcesso>) {
    }

    public registrarRemessa(): ng.IPromise<{}> {
        return this.peticaoFisicaService.registrar(this.cmd)
            .then(() => {
                this.messagesService.success("Remessa registrada com sucesso!");
                return this.$state.go("app.tarefas.minhas-tarefas");
        });
    }

    public showSedex(): boolean {
        if (this.cmd.formaRecebimento && this.cmd.formaRecebimento === "SEDEX") {
            return true;
        }
        this.cmd.numeroSedex = "";
        return false;
    }
}

recebimento.controller("app.recebimento.peticoes-fisicas.PeticaoFisicaController", PeticaoFisicaController);
export default recebimento;