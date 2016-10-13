import ElementArrayFinder = protractor.ElementArrayFinder;
import helpers = require("protractor-helpers");

export class DevolucaoAssinaturaPage {

    private itens: ElementArrayFinder = element.all(by.repeater("devolucao in vm.devolucoes"));

    public selecionarDevolucoes(): void {
        this.itens.all(by.css("[type=checkbox]")).click();
    }

    public assinar(): void {
        element(by.id("btnAssinar")).click();
    }

}