import IStateService = angular.ui.IStateService;

import {Classe, Preferencia, Remessa, Sigilo} from "../../services/model";
import {DevolucaoService, DevolverRemessaCommand} from "../devolucao/devolucao.service";
import {PreautuarRecursalCommand, PreautuacaoRecursalService} from "./preautuacao-recursal.service";
import preautuacaoRecursal from "./preautuacao-recursal.module";

import "./preautuacao-recursal.service";

/**
 * Controlador responsável por mediar as interações entre o front-end e o back-end.
 * 
 * @author anderson.araujo
 * @since 23.05.2016
 */
export class PreautuacaoRecursalController {

    public classe: Classe;
    public preferencias: Array<Preferencia>;

    public cmdPreautuar: PreautuarRecursalCommand = new PreautuarRecursalCommand();
    public cmdDevolver: DevolverRemessaCommand = new DevolverRemessaCommand();

    public path = {
        id: "tarefas.preautuacao-recursal",
        translation: "Preautuação Recursal",
        uisref: "app.tarefas.recebimento-preautuacao-recursal",
        parent: "tarefas"
    };

    public static $inject = ["$state", "app.recebimento.preautuacao-recursal.PreautuacaoRecursalService",
            "app.recebimento.preautuacao-devolucao.DevolucaoService", "classes", "remessa", "sigilos",
            "messagesService"];

    public constructor(private $state: IStateService,
            private preautuacaoRecursalService: PreautuacaoRecursalService, private devolucaoService: DevolucaoService,
            public classes: Classe[], public remessa: Remessa, public sigilos: Sigilo[],
            private messagesService: app.support.messaging.MessagesService) {
        this.cmdPreautuar.protocoloId = remessa.protocolo;
        this.cmdPreautuar.sigilo = remessa.sigilo;
        this.cmdDevolver.protocoloId = remessa.protocolo;
    }

    /*
     * Carrega as preferências da classe selecionada.
     * 
     * @return Array de objetos Preferencia.
     */
    public carregarPreferencias(): void {
         this.cmdPreautuar.classeId = this.classe.sigla;
         this.preferencias = this.classe.preferencias;
    }

    /*
     * Realiza a préautuação do processo recursal.
     */
    public preautuarProcessoRecursal(): ng.IPromise<{}> {
        return this.preautuacaoRecursalService.preautuarRecursal(this.cmdPreautuar)
                .then(() => {
            this.messagesService.success("Remessa recursal preautuada com sucesso.");
            return this.$state.go("app.tarefas.minhas-tarefas");
        }, () => {
                this.messagesService.error("Erro ao preautuar remessa recursal.");
        });
    }

    public devolver(): ng.IPromise<{}> {
        return this.devolucaoService.devolver(this.cmdDevolver)
                .then(() => {
            this.messagesService.success("Remessa devolvida com sucesso.");
            return this.$state.go("app.tarefas.minhas-tarefas");
        }, () => {
            this.messagesService.error("Erro ao devolver remessa.");
        });
    }

}

preautuacaoRecursal.controller("app.recebimento.preautuacao-recursal.PreautuacaoRecursalController",
        PreautuacaoRecursalController);
export default preautuacaoRecursal;