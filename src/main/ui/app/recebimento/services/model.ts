export class Preferencia {
    public constructor(public id: number, public nome: string) {}
}

export class Classe {
    public constructor(public sigla: string, public nome: string, public preferencias: Array<Preferencia>) { }
}

export class Remessa {
    public constructor (public protocolo: number, public classe: string, public qtdVolumes: number,
            public qtdApensos: number, public formaRecebimento: string, public numeroSedex: string,
            public sigilo: string) {}
}

export class Processo {
    public constructor(public processoId: number, public remessa: Remessa) {}
}

export class Sigilo {
    public constructor (public nome: string, public descricao: string) {}
}

export class TipoProcesso {
    public constructor(public id: string, public nome: string) {}
}

export class TipoDocumento {
    public constructor(public id: number, public descricao: string) {}
}

export interface Modelo {
    id: number;
    tipoDocumento: TipoDocumento;
    nome: string;
    documento: number;
}