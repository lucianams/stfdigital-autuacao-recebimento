export class Preferencia {

    constructor(public id: number, public nome: string) { }
}

export class Classe {
    
    constructor(public sigla: string, public nome: string, public preferencias: Array<Preferencia>) { }
}

export class Remessa {

    constructor (public protocolo: number, public classe: string, public qtdVolumes: number,
            public qtdApensos: number, public formaRecebimento: string, public numeroSedex: string,
            public sigilo: string) { }
}

export class Processo {

	constructor (public processoId: number, public remessa: Remessa) { }
}

export class Sigilo {
	
	constructor (public nome: string, public descricao: string) { }
}

export class TipoProcesso {

    constructor(public id: string, public nome: string) { }
}

export class TipoDocumento {
	constructor(public id: number, public descricao: string) { }
}

export interface Modelo {
	id: number;
	tipoDocumento: TipoDocumento;
	nome: string;
	documento: number;
}