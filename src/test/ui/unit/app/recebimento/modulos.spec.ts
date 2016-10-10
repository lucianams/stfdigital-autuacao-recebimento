import dashlets from "recebimento/dashlets";
import devolucaoAssinatura from "recebimento/devolucao-assinatura";
import peticoesFisicas from "recebimento/peticoes-fisicas";
import preautuacao from "recebimento/preautuacao";
import preautuacaoDevolucao from "recebimento/preautuacao-devolucao";
import preautuacaoRecursal from "recebimento/preautuacao-recursal";
import preparacaoOficioDevolucao from "recebimento/preparacao-oficio-devolucao";
import services from "recebimento/services";

describe("Deveria carregar os módulos do contexto", () => {

    it("Deveria carregar o módulo dashlets", () => {
        expect(dashlets).toBeDefined();
    });

    it("Deveria carregar o módulo devolucao-assinatura", () => {
        expect(devolucaoAssinatura).toBeDefined();
    });

    it("Deveria carregar o módulo peticoes-fisicas", () => {
        expect(peticoesFisicas).toBeDefined();
    });

    it("Deveria carregar o módulo preautuacao", () => {
        expect(preautuacao).toBeDefined();
    });

    it("Deveria carregar o módulo preautuacaoDevolucao", () => {
        expect(preautuacaoDevolucao).toBeDefined();
    });

    it("Deveria carregar o módulo preautuacaoRecursal", () => {
        expect(preautuacaoRecursal).toBeDefined();
    });

    it("Deveria carregar o módulo preautuacaoRecursal", () => {
        expect(preparacaoOficioDevolucao).toBeDefined();
    });

    it("Deveria carregar o módulo services", () => {
        expect(services).toBeDefined();
    });

});